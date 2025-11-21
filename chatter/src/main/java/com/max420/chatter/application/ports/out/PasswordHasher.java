package com.max420.chatter.application.ports.out;

import com.max420.chatter.domain.models.user.HashedPassword;
import com.max420.chatter.domain.models.user.Password;

public interface PasswordHasher {
    HashedPassword hash(Password password);
    boolean compare(HashedPassword hashedPassword, Password password);
}
