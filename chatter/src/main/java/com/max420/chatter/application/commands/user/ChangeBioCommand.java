package com.max420.chatter.application.commands.user;

import com.max420.chatter.domain.models.user.Bio;

import java.util.Objects;

public record ChangeBioCommand(Bio bio) {
    public ChangeBioCommand {
        Objects.requireNonNull(bio);
    }
}
