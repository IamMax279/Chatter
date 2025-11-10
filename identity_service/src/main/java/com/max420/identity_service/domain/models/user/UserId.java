package com.max420.identity_service.domain.models.user;

import java.util.UUID;

public record UserId(String value) {
    public static UserId newId() {
        return new UserId(UUID.randomUUID().toString());
    }
}
