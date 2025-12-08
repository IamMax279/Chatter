package com.max420.chatter.infrastructure.persistence.post.impl;

import com.max420.chatter.application.ports.out.PostRepository;
import com.max420.chatter.domain.models.post.Post;
import com.max420.chatter.domain.models.post.PostId;
import com.max420.chatter.infrastructure.persistence.post.JpaPostRepository;

import java.util.Optional;

public class JpaPostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;

    public JpaPostRepositoryImpl(JpaPostRepository jpaPostRepository) {
        this.jpaPostRepository = jpaPostRepository;
    }

    @Override
    public void save(Post post) {

    }

    @Override
    public Optional<Post> findById(PostId id) {
        return Optional.empty();
    }
}
