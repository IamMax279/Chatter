package com.max420.chatter.domain.exceptions.post;

import com.max420.chatter.domain.exceptions.DomainException;

public class AuthorNotFoundException extends DomainException {
  public AuthorNotFoundException(String message) {
    super(message);
  }
}
