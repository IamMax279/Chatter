package com.max420.chatter.application.ports.in;

import com.max420.chatter.application.commands.auth.LoginCommand;
import com.max420.chatter.application.commands.auth.RegisterUserCommand;
import com.max420.chatter.domain.models.user.UserId;

public interface AuthPort {
    UserId register(RegisterUserCommand command);
    String login(LoginCommand command);
}
