package com.max420.identity_service.domain.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private final UserId id;
    private final Email email;
    private Password password;
    private Username username;
    private Set<Role> roles;
    private final Instant createdAt;

    public User(UserId id, Email email) {
        this.id = id;
        this.email = email;
        this.createdAt = Instant.now();
    }
}
