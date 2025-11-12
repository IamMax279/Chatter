package com.max420.identity_service.domain.models.user;

import com.max420.identity_service.domain.exceptions.InvalidUsernameException;

public record Username(String value) {
    public Username(String value) {
        if (value.isBlank() || value.length() < 3) {
            throw new InvalidUsernameException("Username must be at least 3 characters long.");
        }

        this.value = value;
    }
}
