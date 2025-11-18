package com.max420.identity_service.application.ports.in;

import com.max420.identity_service.application.commands.ChangeUsernameCommand;

public interface ChangeUsernameUseCase {
    void execute(ChangeUsernameCommand command);
}
