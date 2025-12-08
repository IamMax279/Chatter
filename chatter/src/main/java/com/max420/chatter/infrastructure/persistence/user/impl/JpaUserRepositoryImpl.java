package com.max420.chatter.infrastructure.persistence.user.impl;

import com.max420.chatter.application.ports.out.UserRepository;
import com.max420.chatter.domain.models.user.Email;
import com.max420.chatter.domain.models.user.User;
import com.max420.chatter.domain.models.user.UserId;
import com.max420.chatter.infrastructure.persistence.user.JpaUserRepository;
import com.max420.chatter.infrastructure.persistence.user.UserEntity;
import com.max420.chatter.infrastructure.persistence.user.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepositoryImpl implements UserRepository {
    private final JpaUserRepository userRepository;
    private final UserMapper mapper;

    public JpaUserRepositoryImpl(UserMapper mapper, JpaUserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        userRepository.save(mapper.toUserEntity(user));
    }

    @Override
    public void update(UserEntity entity) {
        userRepository.save(entity);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userRepository.findById(userId.value())
                .map(mapper::toUser);
    }

    @Override
    public Optional<UserEntity> findByEmail(Email email) {
        return userRepository.findByEmail(email.value());
    }

    @Override
    public Optional<User> findByEmailAndMap(Email email) {
        return userRepository.findByEmail(email.value())
                .map(mapper::toUser);
    }

    @Override
    public boolean existsById(UserId userId) {
        return userRepository.existsById(userId.value());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return userRepository.existsByEmail(email.value());
    }
}
