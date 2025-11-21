package com.max420.chatter.domain.exceptions;

public class EmailTakenException extends DomainException {
    public EmailTakenException(String message) {
        super(message);
    }
}
