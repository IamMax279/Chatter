package com.max420.identity_service.domain.exceptions;

public class UsernameTakenException extends DomainException {
    public UsernameTakenException(String message) {
        super(message);
    }
}
