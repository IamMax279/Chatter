package com.max420.identity_service.domain.models.user;

import com.max420.identity_service.domain.exceptions.InvalidPasswordException;
import com.max420.identity_service.domain.exceptions.InvalidUsernameException;
import com.max420.identity_service.domain.exceptions.NoRolesException;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class User {
    private final UserId id;
    private final Email email;
    private Password password;
    private Username username;
    private Set<Role> roles;
    private boolean isActive;
    private boolean isVerified;
    private final Instant createdAt;

    // For registering a new user
    public User(UserId id, Email email, Username username, Password password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = new HashSet<>();
        this.roles.add(Role.USER);
        this.isActive = true;
        this.isVerified = false;
        this.createdAt = Instant.now();
    }

    // For reconstructing a user from the db
    public User (
            UserId id,
            Email email,
            Password password,
            Username username,
            Set<Role> roles,
            boolean isActive,
            boolean isVerified,
            Instant createdAt
    ) {
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("User must be assigned a role");
        }

        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = new HashSet<>(roles);
        this.isActive = isActive;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        if (this.roles.size() <= 1) {
            throw new NoRolesException("This user only has one role");
        }

        this.roles.remove(role);
    }

    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

    public void changePassword(Password newPassword) {
        Objects.requireNonNull(newPassword);
        if (this.password.equals(newPassword)) {
            throw new InvalidPasswordException("New password must be different from the previous one");
        }

        this.password = newPassword;
    }

    public void changeUsername(Username username) {
        Objects.requireNonNull(username);
        if (this.username.equals(username)) {
            return;
        }

        this.username = username;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void verify() {
        this.isVerified = true;
    }

    public boolean canLogin() {
        return this.isActive && this.isVerified;
    }
}
