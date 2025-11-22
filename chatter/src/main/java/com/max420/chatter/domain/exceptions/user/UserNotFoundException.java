package com.max420.chatter.domain.exceptions.user;

import com.max420.chatter.domain.exceptions.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
