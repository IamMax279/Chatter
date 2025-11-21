package com.max420.chatter.domain.models.user;

public record HashedPassword(String value) {
    public HashedPassword {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Invalid hashed password.");
        }
    }
}
