package com.max420.identity_service.application.commands.user;

import com.max420.identity_service.domain.models.user.Username;

import java.util.Objects;

public record ChangeUsernameCommand(
        Username newUsername
) {
    public ChangeUsernameCommand {
        Objects.requireNonNull(newUsername);
    }
}
