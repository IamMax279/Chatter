package com.max420.chatter.application.services;

import com.max420.chatter.application.commands.auth.LoginCommand;
import com.max420.chatter.application.commands.auth.RegisterUserCommand;
import com.max420.chatter.application.ports.in.AuthPort;
import com.max420.chatter.application.ports.out.JwtService;
import com.max420.chatter.application.ports.out.PasswordHasher;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.domain.exceptions.user.EmailTakenException;
import com.max420.chatter.domain.exceptions.user.InvalidPasswordException;
import com.max420.chatter.domain.exceptions.user.UserNotFoundException;
import com.max420.chatter.domain.models.user.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements AuthPort {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final JwtService jwtService;

    public AuthService(
            @Lazy UserRepository userRepository,
            PasswordHasher passwordHasher,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.jwtService = jwtService;
    }

    @Override
    public UserId register(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new EmailTakenException("Account with this email already exists");
        }

        HashedPassword hashedPassword = passwordHasher.hash(command.password());

        UserId id = UserId.newId();
        Email email = command.email();
        Username username = command.username();

        User user = new User(id, email, username, hashedPassword);
        userRepository.save(user);

        return id;
    }

    @Override
    public String login(LoginCommand command) {
        Optional<User> user = userRepository.findByEmailAndMap(command.email());
        if (user.isEmpty()) {
            throw new UserNotFoundException("Account with this email doesn't exist");
        }

        if (passwordHasher.compare(user.get().getPassword(), command.password())) {
            return jwtService.generateToken(user.get().getId().value().toString(), command.email().value(), user.get().getRoles());
        } else {
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
