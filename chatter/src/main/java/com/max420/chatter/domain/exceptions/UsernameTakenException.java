package com.max420.chatter.domain.exceptions;

public class UsernameTakenException extends DomainException {
    public UsernameTakenException(String message) {
        super(message);
    }
}
