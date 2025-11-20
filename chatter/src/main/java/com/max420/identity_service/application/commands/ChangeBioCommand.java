package com.max420.identity_service.application.commands;

import com.max420.identity_service.domain.models.user.Bio;

import java.util.Objects;

public record ChangeBioCommand(Bio bio) {
    public ChangeBioCommand {
        Objects.requireNonNull(bio);
    }
}
