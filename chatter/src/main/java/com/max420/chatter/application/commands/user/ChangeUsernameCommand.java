package com.max420.chatter.application.commands.user;

import com.max420.chatter.domain.models.user.Username;

import java.util.Objects;

public record ChangeUsernameCommand(
        Username newUsername
) {
    public ChangeUsernameCommand {
        Objects.requireNonNull(newUsername);
    }
}
