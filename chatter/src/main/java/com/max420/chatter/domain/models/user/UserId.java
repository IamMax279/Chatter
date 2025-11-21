package com.max420.chatter.domain.models.user;

import java.util.Random;

public record UserId(Long value) {
    public static UserId newId() {
        return new UserId(new Random().nextLong());
    }
}
