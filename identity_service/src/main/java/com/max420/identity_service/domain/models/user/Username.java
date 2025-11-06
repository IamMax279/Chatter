package com.max420.identity_service.domain.models.user;

public record Username(String value) {
    public Username(String value) {
        if (value.isBlank() || value.length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long.");
        }

        this.value = value;
    }
}
