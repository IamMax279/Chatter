package com.max420.chatter.domain.exceptions;

public class InvalidUsernameException extends DomainException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
