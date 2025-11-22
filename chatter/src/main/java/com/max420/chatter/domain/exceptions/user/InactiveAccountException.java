package com.max420.chatter.domain.exceptions.user;

import com.max420.chatter.domain.exceptions.DomainException;

public class InactiveAccountException extends DomainException {
    public InactiveAccountException(String message) {
        super(message);
    }
}
