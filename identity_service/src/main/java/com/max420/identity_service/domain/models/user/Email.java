package com.max420.identity_service.domain.models.user;

import java.util.regex.Pattern;

public record Email(String value) {
    public Email(String value) {
        Pattern pattern = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b");
        if (pattern.matcher(value)) {

        }
    }
}
