package com.max420.chatter.application.services;

import com.max420.chatter.application.commands.post.CreatePostCommand;
import com.max420.chatter.application.ports.in.PostPort;
import com.max420.chatter.domain.models.post.Content;
import com.max420.chatter.domain.models.post.PostId;
import com.max420.chatter.domain.models.post.Title;

public class PostService implements PostPort {

    @Override
    public PostId createPost(CreatePostCommand command) {
        PostId id = PostId.newPostId();
        Title title = command.title();
        Content content = command.content();


    }
}
