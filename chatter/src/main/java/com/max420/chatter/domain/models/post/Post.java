package com.max420.chatter.domain.models.post;

import com.max420.chatter.domain.models.user.UserId;
import lombok.Getter;

@Getter
public class Post {
    private final PostId postId;
    private final Title title;
    private final Content content;
    // TODO: reference community
    // TODO: reference upvote count
    // author reference
    private final UserId authorId;

    // create new post
    public Post(Title title, Content content, UserId authorId) {
        this.postId = PostId.newPostId();
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    // recreate post from the db
    public Post(
        PostId postId,
        Title title,
        Content content,
        UserId authorId
    ) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
}
