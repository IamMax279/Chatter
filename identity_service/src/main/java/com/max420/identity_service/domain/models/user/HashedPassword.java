package com.max420.identity_service.domain.models.user;

import java.util.Objects;

public record HashedPassword(String value) {
    public HashedPassword(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }
}
