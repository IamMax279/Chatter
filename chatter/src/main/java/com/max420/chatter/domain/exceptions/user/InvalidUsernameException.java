package com.max420.chatter.domain.exceptions.user;

import com.max420.chatter.domain.exceptions.DomainException;

public class InvalidUsernameException extends DomainException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
