package com.max420.identity_service.application.ports.out;

import com.max420.identity_service.domain.models.user.HashedPassword;
import com.max420.identity_service.domain.models.user.Password;

public interface PasswordHasher {
    HashedPassword hash(Password password);
    boolean compare(HashedPassword hashedPassword, Password password);
}
