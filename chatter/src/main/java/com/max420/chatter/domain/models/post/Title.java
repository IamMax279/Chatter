package com.max420.chatter.domain.models.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.max420.chatter.domain.exceptions.post.InvalidTitleLengthException;

public record Title(String value) {
    public Title {
        if (value.length() < 5 || value.length() > 120) {
            throw new InvalidTitleLengthException("Title must be 5 to 120 characters long");
        }
    }

    @JsonCreator
    public static Title fromString(String value) {
        return new Title(value);
    }
}
