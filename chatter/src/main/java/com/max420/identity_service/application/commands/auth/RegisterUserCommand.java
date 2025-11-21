package com.max420.identity_service.application.commands.auth;

import com.max420.identity_service.domain.models.user.Email;
import com.max420.identity_service.domain.models.user.Password;
import com.max420.identity_service.domain.models.user.Username;

import java.util.Objects;

public record RegisterUserCommand(
        Email email,
        Password password,
        Username username
) {
    public RegisterUserCommand {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Objects.requireNonNull(username);
    }
}
