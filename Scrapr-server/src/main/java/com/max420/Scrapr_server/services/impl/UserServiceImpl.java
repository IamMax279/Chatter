package com.max420.Scrapr_server.services.impl;

import com.max420.Scrapr_server.dto.user.UserRegistrationDto;
import com.max420.Scrapr_server.dto.user.UserSignInDto;
import com.max420.Scrapr_server.models.User;
import com.max420.Scrapr_server.repository.UserRepository;
import com.max420.Scrapr_server.services.JwtService;
import com.max420.Scrapr_server.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

    @Override
    public String signIn(UserSignInDto userDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtService.generateToken(userDetails.getUsername());
    }

    private User mapToUser(UserRegistrationDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
    }
}
