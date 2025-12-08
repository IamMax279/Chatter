package com.max420.chatter.infrastructure.persistence.post;

import com.max420.chatter.domain.models.post.Post;
import com.max420.chatter.infrastructure.persistence.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostEntity toPostEntity(Post post, UserEntity author) {
        return PostEntity.builder()
                .title(post.getTitle().value())
                .content(post.getContent().value())
                .author(author)
                .build();
    }
}
