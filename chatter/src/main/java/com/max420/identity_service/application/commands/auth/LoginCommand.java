package com.max420.identity_service.application.commands.auth;

import com.max420.identity_service.domain.models.user.Email;
import com.max420.identity_service.domain.models.user.Password;

import java.util.Objects;

public record LoginCommand(
        Email email,
        Password password
) {
    public LoginCommand {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
    }
}
