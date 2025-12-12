package com.max420.chatter.adapters.in.dto.user;

public record SignInRequest(
        String email,
        String password
) {
}
