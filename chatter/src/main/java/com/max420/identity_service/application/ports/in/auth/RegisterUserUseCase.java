package com.max420.identity_service.application.ports.in.auth;

import com.max420.identity_service.application.commands.auth.RegisterUserCommand;
import com.max420.identity_service.domain.models.user.UserId;

public interface RegisterUserUseCase {
    UserId execute(RegisterUserCommand command);
}
