package com.max420.chatter.domain.exceptions.post;

import com.max420.chatter.domain.exceptions.DomainException;

public class InvalidPostLengthException extends DomainException {
    public InvalidPostLengthException(String message) {
        super(message);
    }
}
