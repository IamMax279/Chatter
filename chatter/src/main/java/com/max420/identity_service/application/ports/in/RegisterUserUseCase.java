package com.max420.identity_service.application.ports.in;

import com.max420.identity_service.application.commands.RegisterUserCommand;
import com.max420.identity_service.domain.models.user.UserId;

public interface RegisterUserUseCase {
    UserId execute(RegisterUserCommand command);
}
