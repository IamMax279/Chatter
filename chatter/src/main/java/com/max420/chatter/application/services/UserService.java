package com.max420.chatter.application.services;

import com.max420.chatter.application.commands.user.ChangeBioCommand;
import com.max420.chatter.application.commands.user.ChangeUsernameCommand;
import com.max420.chatter.application.ports.in.UserPort;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.domain.exceptions.user.UserNotFoundException;
import com.max420.chatter.domain.models.user.Email;
import com.max420.chatter.domain.models.user.User;
import com.max420.chatter.adapters.in.dto.auth.AuthPrincipalDto;
import com.max420.chatter.infrastructure.persistence.user.UserEntity;
import com.max420.chatter.infrastructure.persistence.user.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserPort {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void changeUsername(ChangeUsernameCommand command) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof AuthPrincipalDto)) {
            throw new IllegalStateException("The auth principal object is invalid");
        }

        UserEntity entity = userRepository.findByEmail(new Email(((AuthPrincipalDto) principal).email()))
                .orElseThrow(() -> new UserNotFoundException("User with such email address does not exist"));
        User user = mapper.toUser(entity);
        user.changeUsername(
                command.newUsername(),
                userRepository::existsByUsername
        );

        mapper.updateEntity(entity, user);
        userRepository.update(entity);
    }

    @Override
    public void changeBio(ChangeBioCommand command) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof AuthPrincipalDto)) {
            throw new IllegalStateException("The auth principal object is invalid");
        }

        UserEntity entity = userRepository.findByEmail(new Email(((AuthPrincipalDto) principal).email()))
                .orElseThrow(() -> new UserNotFoundException("User with such email address does not exist"));
        User user = mapper.toUser(entity);
        user.changeBio(command.bio());

        mapper.updateEntity(entity, user);
        userRepository.update(entity);
    }
}
