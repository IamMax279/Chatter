package com.max420.identity_service.application.services;

import com.max420.identity_service.application.commands.ChangeUsernameCommand;
import com.max420.identity_service.application.ports.in.ChangeUsernameUseCase;
import com.max420.identity_service.application.ports.out.UserRepository;
import com.max420.identity_service.domain.exceptions.UserNotFoundException;
import com.max420.identity_service.domain.models.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeUsernameService implements ChangeUsernameUseCase {
    private final UserRepository userRepository;

    public ChangeUsernameService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(ChangeUsernameCommand command) {
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new UserNotFoundException("User with such email address does not exist"));

        user.changeUsername(command.newUsername());
        userRepository.save(user);
    }
}
