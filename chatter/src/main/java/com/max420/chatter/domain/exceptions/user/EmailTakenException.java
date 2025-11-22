package com.max420.chatter.domain.exceptions.user;

import com.max420.chatter.domain.exceptions.DomainException;

public class EmailTakenException extends DomainException {
    public EmailTakenException(String message) {
        super(message);
    }
}
