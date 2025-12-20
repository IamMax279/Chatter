package com.max420.chatter.adapters.out.persistence;

import com.max420.chatter.application.ports.out.PostRepository;
import com.max420.chatter.domain.exceptions.post.AuthorNotFoundException;
import com.max420.chatter.domain.models.post.Post;
import com.max420.chatter.domain.models.post.PostId;
import com.max420.chatter.infrastructure.persistence.post.JpaPostRepository;
import com.max420.chatter.infrastructure.persistence.post.PostMapper;
import com.max420.chatter.infrastructure.persistence.user.JpaUserRepository;
import com.max420.chatter.infrastructure.persistence.user.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaPostRepositoryImpl implements PostRepository {
    private final JpaPostRepository postRepository;
    private final JpaUserRepository userRepository;
    private final PostMapper mapper;

    public JpaPostRepositoryImpl(
            @Lazy JpaPostRepository postRepository,
            JpaUserRepository userRepository,
            PostMapper mapper
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Post post) {
        UserEntity entity = userRepository.findById(post.getAuthorId().value())
                .orElseThrow(() -> new AuthorNotFoundException("Post author not found"));

        postRepository.save(mapper.toPostEntity(post, entity));
    }

    @Override
    public Optional<Post> findById(PostId id) {
        return postRepository.findById(id.value()).map(mapper::toPost);
    }
}
