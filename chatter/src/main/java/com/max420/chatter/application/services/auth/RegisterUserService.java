package com.max420.chatter.application.services.auth;

import com.max420.chatter.application.commands.auth.RegisterUserCommand;
import com.max420.chatter.application.ports.in.auth.RegisterUserUseCase;
import com.max420.chatter.application.ports.out.PasswordHasher;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.domain.exceptions.user.EmailTakenException;
import com.max420.chatter.domain.models.user.*;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserUseCase {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public RegisterUserService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public UserId execute(RegisterUserCommand command) {
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
}
