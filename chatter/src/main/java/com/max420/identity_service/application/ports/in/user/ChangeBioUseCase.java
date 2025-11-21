package com.max420.identity_service.application.ports.in.user;

import com.max420.identity_service.application.commands.user.ChangeBioCommand;

public interface ChangeBioUseCase {
    void execute(ChangeBioCommand command);
}
