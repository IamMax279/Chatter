package com.max420.Scrapr_server.services;

import com.max420.Scrapr_server.dto.user.UserRegistrationDto;
import com.max420.Scrapr_server.dto.user.UserSignInDto;
import com.max420.Scrapr_server.models.User;

import javax.naming.AuthenticationException;

public interface UserService {
    User createUser(UserRegistrationDto userDto) throws IllegalArgumentException;
    String signIn(UserSignInDto userSignInDto);
}
