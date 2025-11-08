package com.max420.identity_service.domain.models.user;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User {
    private final UserId id;
    private final Email email;
    private Password password;
    private Username username;
    private Set<Role> roles;
    private boolean isActive;
    private boolean isVerified;
    private final Instant createdAt;

    public User(UserId id, Email email, Username username) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.roles = new HashSet<>();
        this.roles.add(Role.USER);
        this.isActive = true;
        this.isVerified = false;
        this.createdAt = Instant.now();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

    public void changePassword(Password oldPassword, Password newPassword) {
        if (oldPassword.equals(newPassword)) {
            throw new IllegalArgumentException("New password must be different from the previous one.");
        }

        this.password = newPassword;
    }

    public boolean verifyPassword(Password passwordCandidate) {
        return this.password.value().matches(passwordCandidate.value());
    }

    public void changeUsername(Username username) {
        if (username.value().isBlank() || username.value().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long.");
        }
        if (this.username == username) {
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
}
