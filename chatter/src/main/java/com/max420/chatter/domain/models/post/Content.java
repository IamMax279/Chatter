package com.max420.chatter.domain.models.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.max420.chatter.domain.exceptions.post.InvalidPostLengthException;

public record Content(String value) {
    public Content {
        if (value.length() < 5 || value.length() > 500) {
            throw new InvalidPostLengthException("Post content must be 5 to 500 characters long");
        }
    }

    @JsonCreator
    public static Content fromString(String value) {
        return new Content(value);
    }
}
