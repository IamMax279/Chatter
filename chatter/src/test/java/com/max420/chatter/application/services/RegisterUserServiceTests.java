package com.max420.chatter.application.services;

import com.max420.chatter.application.commands.auth.RegisterUserCommand;
import com.max420.chatter.application.ports.out.PasswordHasher;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.application.services.auth.RegisterUserService;
import com.max420.chatter.domain.exceptions.EmailTakenException;
import com.max420.chatter.domain.models.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegisterUserServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordHasher passwordHasher;

    @InjectMocks
    private RegisterUserService registerUserService;

    @Test
    public void should_createUser_when_correctData() {
        RegisterUserCommand command = new RegisterUserCommand(
                new Email("test@email.com"),
                new Password("test1234"),
                new Username("maks420")
        );

        UserId result = registerUserService.execute(command);

        assertNotNull(result);
        verify(userRepository).save(any(User.class));
        verify(userRepository).save(argThat(user ->
                user.getEmail().value().equals("test@email.com") &&
                user.getUsername().value().equals("maks420")
        ));
        verify(passwordHasher).hash(command.password());
    }

    @Test
    public void should_throwError_when_emailAddressTaken() {
        when(userRepository.existsByEmail(new Email("test@email.com"))).thenReturn(true);

        RegisterUserCommand command = new RegisterUserCommand(
                new Email("test@email.com"),
                new Password("test1234"),
                new Username("maks420")
        );

        assertThrows(EmailTakenException.class, () -> registerUserService.execute(command));
    }
}
