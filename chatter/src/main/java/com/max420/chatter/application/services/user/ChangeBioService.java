package com.max420.chatter.application.services.user;

import com.max420.chatter.application.commands.user.ChangeBioCommand;
import com.max420.chatter.application.ports.in.user.ChangeBioUseCase;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.domain.exceptions.user.UserNotFoundException;
import com.max420.chatter.domain.models.user.Email;
import com.max420.chatter.domain.models.user.User;
import com.max420.chatter.infrastructure.adapters.out.persistence.user.UserEntity;
import com.max420.chatter.infrastructure.adapters.out.persistence.user.UserEntityMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChangeBioService implements ChangeBioUseCase {
    private final UserRepository userRepository;
    private final UserEntityMapper mapper;

    public ChangeBioService(UserRepository userRepository, UserEntityMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void execute(ChangeBioCommand command) {
        Object email = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(email instanceof String)) {
            throw new IllegalStateException("The auth principal is not a string");
        }

        UserEntity entity = userRepository.findByEmail(new Email((String) email))
                .orElseThrow(() -> new UserNotFoundException("User with such email address does not exist"));
        User user = mapper.toUser(entity);
        user.changeBio(command.bio());

        mapper.updateEntity(entity, user);
        userRepository.update(entity);
    }
}
