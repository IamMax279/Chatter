package com.max420.identity_service.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void createUser_shouldCreateUserIfDataIsValid() {
        UserRegistrationDto userDto = new UserRegistrationDto(
                "test@gmail.com",
                "password123",
                "test"
        );
        when(userRepository.save(any(User.class))).thenReturn(new User(
                "test@gmail.com",
                "pass1234",
                "maks"
        ));

        User result = userService.createUser(userDto);

        assertNotNull(result);

        // verify is the .save() method has been called on userRepository
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_shouldThrowErrorIfEmailAlreadyInDb() {
        UserRegistrationDto userDto = new UserRegistrationDto(
                "test123@mail.com",
                "password123",
                "test1234"
        );

        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> userService.createUser(userDto));
        verify(userRepository, never()).save(any(User.class));
    }
}
