package com.max420.chatter.adapters.in.dto.user;

public record RegisterUserRequest(
        String email,
        String username,
        String password
) {
}
