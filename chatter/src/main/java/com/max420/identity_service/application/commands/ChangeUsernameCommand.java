package com.max420.identity_service.application.commands;

import com.max420.identity_service.domain.models.user.Email;
import com.max420.identity_service.domain.models.user.Username;

import java.util.Objects;

public record ChangeUsernameCommand(
        Username newUsername,
        Email email
) {
    public ChangeUsernameCommand {
        Objects.requireNonNull(newUsername);
        Objects.requireNonNull(email);
    }
}
