package com.max420.identity_service.application.ports.in.auth;

import com.max420.identity_service.application.commands.auth.LoginCommand;

public interface LoginUseCase {
    String execute(LoginCommand command);
}
