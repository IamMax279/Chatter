package com.max420.identity_service.domain.models.user;

import java.util.Objects;

public record HashedPassword(String value) {
    public HashedPassword {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Invalid hashed password.");
        }
    }
}
