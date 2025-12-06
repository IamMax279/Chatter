package com.max420.chatter.application.ports.out;

import com.max420.chatter.domain.models.post.Post;
import com.max420.chatter.domain.models.post.PostId;

import java.util.Optional;

public interface PostRepository {
    void save(Post post);
    Optional<Post> findById(PostId id);
}
