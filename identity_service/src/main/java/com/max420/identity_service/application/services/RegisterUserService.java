package com.max420.identity_service.application.services;

import com.max420.identity_service.application.commands.RegisterUserCommand;
import com.max420.identity_service.application.ports.in.RegisterUserUseCase;
import com.max420.identity_service.application.ports.out.PasswordHasher;
import com.max420.identity_service.application.ports.out.UserRepository;
import com.max420.identity_service.domain.exceptions.EmailTakenException;
import com.max420.identity_service.domain.models.user.UserId;

public class RegisterUserService implements RegisterUserUseCase {
    private UserRepository userRepository;
    private PasswordHasher passwordHasher;

    @Override
    public UserId execute(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new EmailTakenException("Account with this email already exists");
        }

        String
    }
}
