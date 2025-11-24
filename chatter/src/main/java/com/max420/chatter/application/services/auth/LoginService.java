package com.max420.chatter.application.services.auth;

import com.max420.chatter.application.commands.auth.LoginCommand;
import com.max420.chatter.application.ports.in.auth.LoginUseCase;
import com.max420.chatter.application.ports.out.JwtService;
import com.max420.chatter.application.ports.out.PasswordHasher;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.domain.exceptions.user.InvalidPasswordException;
import com.max420.chatter.domain.exceptions.user.UserNotFoundException;
import com.max420.chatter.domain.models.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements LoginUseCase {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordHasher passwordHasher;

    public LoginService(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordHasher passwordHasher
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public String execute(LoginCommand command) {
        Optional<User> user = userRepository.findByEmailAndMap(command.email());
        if (user.isEmpty()) {
            throw new UserNotFoundException("Account with this email doesn't exist");
        }

        if (passwordHasher.compare(user.get().getPassword(), command.password())) {
            return jwtService.generateToken(command.email().value(), user.get().getRoles());
        } else {
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
