package com.max420.identity_service.domain.models.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.max420.identity_service.domain.exceptions.InvalidEmailException;

import java.util.regex.Pattern;

public record Email(String value) {

    @JsonCreator
    public Email(String value) {
        Pattern pattern = Pattern.compile("\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}\\b");
        if (!pattern.matcher(value).matches()) {
            throw new InvalidEmailException("Invalid email.");
        }

        this.value = value;
    }
}
