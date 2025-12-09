package com.max420.chatter.infrastructure.persistence.post;

import com.max420.chatter.domain.models.post.Content;
import com.max420.chatter.domain.models.post.Post;
import com.max420.chatter.domain.models.post.PostId;
import com.max420.chatter.domain.models.post.Title;
import com.max420.chatter.domain.models.user.UserId;
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

    public Post toPost(PostEntity entity) {
        return new Post(
                new PostId(entity.getId()),
                new Title(entity.getTitle()),
                new Content(entity.getContent()),
                new UserId(entity.getAuthor().getId())
        );
    }
}
