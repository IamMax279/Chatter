package com.max420.identity_service.application.ports.out;

import com.max420.identity_service.domain.models.user.Email;
import com.max420.identity_service.domain.models.user.User;
import com.max420.identity_service.domain.models.user.UserId;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UserId userId);
    Optional<User> findByEmail(Email email);
    boolean existsById(UserId userId);
    boolean existsByEmail(Email email);
}
