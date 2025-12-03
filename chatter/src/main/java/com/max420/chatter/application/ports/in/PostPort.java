package com.max420.chatter.application.ports.in;

import com.max420.chatter.application.commands.post.CreatePostCommand;
import com.max420.chatter.domain.models.post.PostId;

public interface PostPort {
    PostId createPost(CreatePostCommand command);
}
