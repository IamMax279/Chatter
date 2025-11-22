package com.max420.chatter.domain.exceptions.user;

import com.max420.chatter.domain.exceptions.DomainException;

public class UsernameTakenException extends DomainException {
    public UsernameTakenException(String message) {
        super(message);
    }
}
