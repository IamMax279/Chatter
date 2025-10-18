package com.max420.auth_service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserSignInDto {
    @NotBlank(message = "Email is required.")
    @Email(message = "Wrong email format.")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
