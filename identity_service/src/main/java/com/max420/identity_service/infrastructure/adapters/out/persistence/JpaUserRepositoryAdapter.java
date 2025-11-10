package com.max420.identity_service.infrastructure.adapters.out.persistence;

import com.max420.identity_service.application.ports.out.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepositoryAdapter extends UserRepository, JpaRepository<UserEntity, Long>{
}
