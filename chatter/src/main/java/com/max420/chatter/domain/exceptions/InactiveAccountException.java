package com.max420.chatter.domain.exceptions;

public class InactiveAccountException extends DomainException {
    public InactiveAccountException(String message) {
        super(message);
    }
}
