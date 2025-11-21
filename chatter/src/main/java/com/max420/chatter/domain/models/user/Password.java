package com.max420.chatter.domain.models.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.max420.chatter.domain.exceptions.InvalidPasswordException;

public record Password(String value) {
    public Password {
        if (value.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters long.");
        }
    }

    @JsonCreator
    public static Password fromString(String value) {
        return new Password(value);
    }
}
