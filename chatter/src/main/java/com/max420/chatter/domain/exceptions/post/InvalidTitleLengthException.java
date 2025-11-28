package com.max420.chatter.domain.exceptions.post;

import com.max420.chatter.domain.exceptions.DomainException;

public class InvalidTitleLengthException extends DomainException {
    public InvalidTitleLengthException(String message) {
        super(message);
    }
}
