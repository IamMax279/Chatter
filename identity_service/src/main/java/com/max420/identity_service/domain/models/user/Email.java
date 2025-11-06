package com.max420.identity_service.domain.models.user;

import java.util.regex.Pattern;

public record Email(String value) {
    public Email(String value) {
        Pattern pattern = Pattern.compile("\\b[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}\\b");
        if (pattern.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email.");
        }

        this.value = value;
    }
}
