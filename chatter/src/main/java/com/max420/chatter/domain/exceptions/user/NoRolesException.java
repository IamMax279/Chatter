package com.max420.chatter.domain.exceptions.user;

import com.max420.chatter.domain.exceptions.DomainException;

public class NoRolesException extends DomainException {
    public NoRolesException(String message) {
        super(message);
    }
}
