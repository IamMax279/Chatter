package com.max420.chatter.infrastructure.persistence.user;

import com.max420.chatter.domain.models.user.*;
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

    public void updateEntity(UserEntity entity, User user) {
        entity.setUsername(user.getUsername().value());
        entity.setBio(user.getBio().value());
        entity.setRoles(user.getRoles());
        entity.setActive(user.isActive());
        entity.setVerified(user.isVerified());
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
