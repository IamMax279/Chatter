package com.max420.identity_service.infrastructure.adapters.out.security;

import com.max420.identity_service.application.ports.out.PasswordHasher;
import com.max420.identity_service.domain.models.user.HashedPassword;
import com.max420.identity_service.domain.models.user.Password;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHasher implements PasswordHasher {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public HashedPassword hash(Password password) {
        return new HashedPassword(encoder.encode(password.value()));
    }

    @Override
    public boolean compare(HashedPassword hashedPassword, Password password) {
        return encoder.matches(hashedPassword.value(), password.value());
    }
}
