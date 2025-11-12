package com.max420.identity_service.application.commands;

import com.max420.identity_service.domain.models.user.Email;
import com.max420.identity_service.domain.models.user.Password;

import java.util.Objects;

public record LoginCommand(
        Email email,
        Password password
) {
    public LoginCommand(Email email, Password password) {
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
    }
}
