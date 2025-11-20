package com.max420.identity_service.application.ports.in;

import com.max420.identity_service.application.commands.ChangeBioCommand;

public interface ChangeBioUseCase {
    void execute(ChangeBioCommand command);
}
