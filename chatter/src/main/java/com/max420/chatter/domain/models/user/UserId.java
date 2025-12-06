package com.max420.chatter.domain.models.user;

import java.util.Objects;
import java.util.Random;

public record UserId(Long value) {
    public UserId {
        Objects.requireNonNull(value);
    }

    public static UserId newId() {
        return new UserId(new Random().nextLong());
    }
}
