package com.max420.chatter.application.services.user;

import com.max420.chatter.application.commands.user.ChangeUsernameCommand;
import com.max420.chatter.application.ports.in.user.ChangeUsernameUseCase;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.domain.exceptions.UserNotFoundException;
import com.max420.chatter.domain.models.user.Email;
import com.max420.chatter.domain.models.user.User;
import com.max420.chatter.infrastructure.adapters.out.persistence.user.UserEntity;
import com.max420.chatter.infrastructure.adapters.out.persistence.user.UserEntityMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChangeUsernameService implements ChangeUsernameUseCase {
    private final UserRepository userRepository;
    private final UserEntityMapper mapper;

    public ChangeUsernameService(UserRepository userRepository, UserEntityMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void execute(ChangeUsernameCommand command) {
        Object email = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(email instanceof String)) {
            throw new IllegalStateException("The auth principal is not a string");
        }

        UserEntity entity = userRepository.findByEmail(new Email((String) email))
                .orElseThrow(() -> new UserNotFoundException("User with such email address does not exist"));
        User user = mapper.toUser(entity);
        user.changeUsername(command.newUsername());

        mapper.updateEntity(entity, user);
        userRepository.update(entity);
    }
}
