package com.max420.identity_service.domain.models.user;

public record Password(String value) {
    public Password(String value) {
        if (value.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }

        this.value = value;
    }
}
