package com.max420.chatter.application.services.auth;

import com.max420.chatter.application.commands.auth.LoginCommand;
import com.max420.chatter.application.ports.in.auth.LoginUseCase;
import com.max420.chatter.application.ports.out.JwtService;
import com.max420.chatter.application.ports.out.PasswordHasher;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.domain.exceptions.InvalidPasswordException;
import com.max420.chatter.domain.exceptions.UserNotFoundException;
import com.max420.chatter.domain.models.user.User;
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

        User user = userRepository.findByEmailAndMap(command.email()).get();
        if (passwordHasher.compare(user.getPassword(), command.password())) {
            return generator.generateToken(command.email().value(), user.getRoles());
        } else {
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
