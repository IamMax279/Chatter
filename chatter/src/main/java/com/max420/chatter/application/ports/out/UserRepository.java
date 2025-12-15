package com.max420.chatter.application.ports.out;

import com.max420.chatter.domain.models.user.Email;
import com.max420.chatter.domain.models.user.User;
import com.max420.chatter.domain.models.user.UserId;
import com.max420.chatter.domain.models.user.Username;
import com.max420.chatter.infrastructure.persistence.user.UserEntity;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    void update(UserEntity entity);
    Optional<User> findById(UserId userId);
    Optional<User> findByEmailAndMap(Email email);
    Optional<UserEntity> findByEmail(Email email);
    boolean existsById(UserId userId);
    boolean existsByEmail(Email email);
    boolean existsByUsername(Username username);
}
