package com.max420.Scrapr_server.services;

import com.max420.Scrapr_server.dto.user.UserRegistrationDto;

public interface UserService {
    void createUser(UserRegistrationDto userDto) throws IllegalArgumentException;
}
