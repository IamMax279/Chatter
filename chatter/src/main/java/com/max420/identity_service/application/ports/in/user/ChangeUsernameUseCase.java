package com.max420.identity_service.application.ports.in.user;

import com.max420.identity_service.application.commands.user.ChangeUsernameCommand;

public interface ChangeUsernameUseCase {
    void execute(ChangeUsernameCommand command);
}
