package com.max420.Scrapr_server.services.impl;

import com.max420.Scrapr_server.dto.user.UserRegistrationDto;
import com.max420.Scrapr_server.models.User;
import com.max420.Scrapr_server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

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
}
