package com.max420.identity_service.application.ports.out;

import com.max420.identity_service.domain.models.user.Password;

public interface PasswordHasher {
    String hash(Password password);
}
