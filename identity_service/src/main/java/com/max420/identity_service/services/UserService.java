package com.max420.identity_service.services;

import com.max420.identity_service.dto.user.UserRegistrationDto;
import com.max420.identity_service.dto.user.UserSignInDto;
import com.max420.identity_service.models.User;

public interface UserService {
    User createUser(UserRegistrationDto userDto) throws IllegalArgumentException;
    String signIn(UserSignInDto userSignInDto);
}
