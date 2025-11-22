package com.max420.chatter.domain.models.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.max420.chatter.domain.exceptions.user.InvalidUsernameException;

public record Username(String value) {
    public Username {
        if (value.isBlank() || value.length() < 3) {
            throw new InvalidUsernameException("Username must be at least 3 characters long.");
        }
    }

    @JsonCreator
    public static Username fromString(String value) {
        return new Username(value);
    }
}
