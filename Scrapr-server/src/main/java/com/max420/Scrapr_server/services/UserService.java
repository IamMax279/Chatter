package com.max420.Scrapr_server.services;

import com.max420.Scrapr_server.dto.user.UserRegistrationDto;
import com.max420.Scrapr_server.models.User;

public interface UserService {
    User createUser(UserRegistrationDto userDto) throws IllegalArgumentException;
}
