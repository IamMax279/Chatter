package com.max420.chatter.adapters.out.security;

import com.max420.chatter.application.ports.out.PasswordHasher;
import com.max420.chatter.domain.models.user.HashedPassword;
import com.max420.chatter.domain.models.user.Password;
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
        return encoder.matches(password.value(), hashedPassword.value());
    }
}
