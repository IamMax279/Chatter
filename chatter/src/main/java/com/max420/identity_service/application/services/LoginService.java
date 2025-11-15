package com.max420.identity_service.application.services;

import com.max420.identity_service.application.commands.LoginCommand;
import com.max420.identity_service.application.ports.in.LoginUseCase;
import com.max420.identity_service.application.ports.out.JwtService;
import com.max420.identity_service.application.ports.out.PasswordHasher;
import com.max420.identity_service.application.ports.out.UserRepository;
import com.max420.identity_service.domain.exceptions.InvalidPasswordException;
import com.max420.identity_service.domain.exceptions.UserNotFoundException;
import com.max420.identity_service.domain.models.user.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {
    private final UserRepository userRepository;
    private final JwtService generator;
    private final PasswordHasher passwordHasher;

    public LoginService(
            UserRepository userRepository,
            JwtService generator,
            PasswordHasher passwordHasher
    ) {
        this.userRepository = userRepository;
        this.generator = generator;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public String execute(LoginCommand command) {
        if (!userRepository.existsByEmail(command.email())) {
            throw new UserNotFoundException("Account with this email doesn't exist");
        }

        User user = userRepository.findByEmail(command.email()).get();
        if (passwordHasher.compare(user.getPassword(), command.password())) {
            return generator.generateToken(); // TODO: zaimplementowac adapter jwt
        } else {
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
