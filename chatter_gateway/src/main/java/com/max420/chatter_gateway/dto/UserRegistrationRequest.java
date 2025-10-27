package com.max420.chatter_gateway.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationRequest {
    private String email;
    private String password;
    private String userName;
}
