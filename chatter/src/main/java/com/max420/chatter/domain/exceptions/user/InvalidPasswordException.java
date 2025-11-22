package com.max420.chatter.domain.exceptions.user;

import com.max420.chatter.domain.exceptions.DomainException;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
