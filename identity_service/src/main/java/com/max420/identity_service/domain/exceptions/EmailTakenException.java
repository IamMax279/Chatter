package com.max420.identity_service.domain.exceptions;

public class EmailTakenException extends DomainException {
    public EmailTakenException(String message) {
        super(message);
    }
}
