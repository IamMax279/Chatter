package com.max420.Scrapr_server.services.impl;

import com.max420.Scrapr_server.dto.user.UserRegistrationDto;
import com.max420.Scrapr_server.models.User;
import com.max420.Scrapr_server.repository.UserRepository;
import com.max420.Scrapr_server.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserRegistrationDto userDto) throws IllegalArgumentException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Account with this email already exists.");
        }

        User user = mapToUser(userDto);
        userRepository.save(user);

        return user;
    }

    private User mapToUser(UserRegistrationDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
    }
}
