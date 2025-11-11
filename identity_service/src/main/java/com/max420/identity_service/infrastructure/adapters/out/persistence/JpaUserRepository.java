package com.max420.identity_service.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepositoryAdapter extends JpaRepository<UserEntity, Long>{
}
