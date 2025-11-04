package com.max420.identity_service.domain.models.user;

import java.time.Instant;
import java.util.Set;

public class User {
    private UserId id;
    private Email email;
    private Password password;
    private Username username;
    private Set<Role> roles;
    private Instant createdAt;
}
