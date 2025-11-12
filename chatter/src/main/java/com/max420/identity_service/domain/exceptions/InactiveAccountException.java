package com.max420.identity_service.domain.exceptions;

public class InactiveAccountException extends DomainException {
    public InactiveAccountException(String message) {
        super(message);
    }
}
