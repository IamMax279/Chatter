package com.max420.identity_service.application.ports.in;

import com.max420.identity_service.application.commands.LoginCommand;

public interface LoginUseCase {
    String execute(LoginCommand command);
}
