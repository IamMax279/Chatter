package com.max420.identity_service.application.commands;

import com.max420.identity_service.domain.models.user.Email;
import com.max420.identity_service.domain.models.user.Password;
import com.max420.identity_service.domain.models.user.Username;

import java.util.Objects;

public record RegisterUserCommand(
        Email email,
        Password password,
        Username username
) {
    public RegisterUserCommand(Email email, Password password, Username username) {
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.username = Objects.requireNonNull(username);
    }
}
