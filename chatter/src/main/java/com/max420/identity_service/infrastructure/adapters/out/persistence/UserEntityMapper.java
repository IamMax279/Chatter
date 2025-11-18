package com.max420.identity_service.infrastructure.adapters.out.persistence;

import com.max420.identity_service.domain.models.user.*;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class UserEntityMapper {
    public UserEntity toUserEntity(User user) {
        return UserEntity.builder()
                .email(user.getEmail().value())
                .password(user.getPassword().value())
                .username(user.getUsername().value())
                .roles(user.getRoles())
                .build();
    }

    public User toUser(UserEntity entity) {
        return new User(
                new UserId(entity.getId()),
                new Email(entity.getEmail()),
                new HashedPassword(entity.getPassword()),
                new Username(entity.getUsername()),
                new Bio(entity.getBio()),
                entity.getRoles(),
                entity.isActive(),
                entity.isVerified(),
                entity.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()
        );
    }
}
