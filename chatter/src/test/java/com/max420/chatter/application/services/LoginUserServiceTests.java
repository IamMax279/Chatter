package com.max420.chatter.application.services;

import com.max420.chatter.application.commands.auth.LoginCommand;
import com.max420.chatter.application.ports.out.JwtService;
import com.max420.chatter.application.ports.out.PasswordHasher;
import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.application.services.auth.LoginService;
import com.max420.chatter.domain.exceptions.InvalidPasswordException;
import com.max420.chatter.domain.exceptions.UserNotFoundException;
import com.max420.chatter.domain.models.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LoginUserServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordHasher passwordHasher;

    @InjectMocks
    private LoginService loginService;

    @Test
    public void should_logUserIn_when_correctCredentials() {
        when(userRepository.existsByEmail(new Email("test@email.com")))
                .thenReturn(true);
        when(userRepository.findByEmailAndMap(new Email("test@email.com")))
                .thenReturn(Optional.of(new User(
                        UserId.newId(),
                        new Email("test@email.com"),
                        new Username("TestUser"),
                        new HashedPassword("hashedPassword123")
                )));
        when(passwordHasher.compare(new HashedPassword("hashedPassword123"), new Password("random123")))
                .thenReturn(true);
        when(jwtService.generateToken(
                "test@email.com",
                Set.of(Role.USER)
        )).thenReturn("token123");

        assertEquals("token123", loginService.execute(new LoginCommand(
                new Email("test@email.com"),
                new Password("random123")
        )));
    }

    @Test
    public void shouldNot_logUserIn_when_passwordsDoNotMatch() {
        when(userRepository.existsByEmail(new Email("test@email.com")))
                .thenReturn(true);
        when(userRepository.findByEmailAndMap(new Email("test@email.com")))
                .thenReturn(Optional.of(new User(
                        UserId.newId(),
                        new Email("test@email.com"),
                        new Username("TestUser"),
                        new HashedPassword("hashedPassword123")
                )));
        when(passwordHasher.compare(new HashedPassword("hashedPassword123"), new Password("random123")))
                .thenReturn(false);

        assertThrows(InvalidPasswordException.class, () -> {
            loginService.execute(new LoginCommand(
                    new Email("test@email.com"),
                    new Password("random123")
            ));
        });
    }

    @Test
    public void shouldNot_logUserIn_when_credentialsNotInDb() {
        when(userRepository.existsByEmail(new Email("test@email.com")))
                .thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> {
            loginService.execute(new LoginCommand(
                    new Email("test@email.com"),
                    new Password("random123")
            ));
        });
    }
}
