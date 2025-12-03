package com.max420.chatter.application.commands.post;

import com.max420.chatter.domain.models.post.Content;
import com.max420.chatter.domain.models.post.Title;

import java.util.Objects;

public record CreatePostCommand(
        Title title,
        Content content
) {
    public CreatePostCommand {
        Objects.requireNonNull(title);
        Objects.requireNonNull(content);
    }
}
