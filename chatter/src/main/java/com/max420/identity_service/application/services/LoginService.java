package com.max420.identity_service.application.services;

import com.max420.identity_service.application.commands.LoginCommand;
import com.max420.identity_service.application.ports.in.LoginUseCase;
import com.max420.identity_service.application.ports.out.JwtTokenGenerator;
import com.max420.identity_service.application.ports.out.PasswordHasher;
import com.max420.identity_service.application.ports.out.UserRepository;
import com.max420.identity_service.domain.exceptions.InvalidPasswordException;
import com.max420.identity_service.domain.exceptions.UserNotFoundException;
import com.max420.identity_service.domain.models.user.User;

public class LoginService implements LoginUseCase {
    private UserRepository userRepository;
    private JwtTokenGenerator generator;
    private PasswordHasher passwordHasher;

    @Override
    public String execute(LoginCommand command) {
        if (!userRepository.existsByEmail(command.email())) {
            throw new UserNotFoundException("Account with this email doesn't exist");
        }

        User user = userRepository.findByEmail(command.email()).get();
        if (passwordHasher.compare(user.getPassword(), command.password())) {
            return generator.generateToken();
        } else {
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
