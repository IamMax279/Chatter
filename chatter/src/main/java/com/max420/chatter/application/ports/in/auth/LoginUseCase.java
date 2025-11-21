package com.max420.chatter.application.ports.in.auth;

import com.max420.chatter.application.commands.auth.LoginCommand;

public interface LoginUseCase {
    String execute(LoginCommand command);
}
