package com.max420.identity_service.infrastructure.adapters.in.controllers;

import com.max420.identity_service.domain.exceptions.EmailTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<?> handleEmailTaken(EmailTakenException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Something went wrong: " + e.getMessage());
    }
}
