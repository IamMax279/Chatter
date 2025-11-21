package com.max420.chatter.application.commands.auth;

import com.max420.chatter.domain.models.user.Email;
import com.max420.chatter.domain.models.user.Password;

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
