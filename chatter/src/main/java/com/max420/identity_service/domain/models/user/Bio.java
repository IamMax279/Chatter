package com.max420.identity_service.domain.models.user;

import com.fasterxml.jackson.annotation.JsonCreator;

public record Bio(String value) {
    @JsonCreator
    public static Bio fromString(String value) {
        return new Bio(value);
    }
}
