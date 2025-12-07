package com.max420.chatter.infrastructure.persistence.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
}
