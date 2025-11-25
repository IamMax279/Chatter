package com.max420.chatter.domain.models.post;

import java.util.Random;

public record PostId(Long value) {
    public static PostId newPostId() {
        return new PostId(new Random().nextLong());
    }
}
