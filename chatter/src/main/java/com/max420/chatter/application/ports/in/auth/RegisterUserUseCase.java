package com.max420.chatter.application.ports.in.auth;

import com.max420.chatter.application.commands.auth.RegisterUserCommand;
import com.max420.chatter.domain.models.user.UserId;

public interface RegisterUserUseCase {
    UserId execute(RegisterUserCommand command);
}
