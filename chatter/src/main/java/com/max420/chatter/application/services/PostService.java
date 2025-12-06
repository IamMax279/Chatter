package com.max420.chatter.application.services;

import com.max420.chatter.application.commands.post.CreatePostCommand;
import com.max420.chatter.application.ports.in.PostPort;
import com.max420.chatter.application.ports.out.PostRepository;
import com.max420.chatter.domain.models.post.Content;
import com.max420.chatter.domain.models.post.Post;
import com.max420.chatter.domain.models.post.PostId;
import com.max420.chatter.domain.models.post.Title;
import com.max420.chatter.domain.models.user.UserId;
import com.max420.chatter.infrastructure.adapters.in.dto.AuthPrincipalDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PostService implements PostPort {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostId createPost(CreatePostCommand command) {
        Object context = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(context instanceof AuthPrincipalDto)) {
            throw new IllegalStateException("The auth principal object is invalid");
        }

        PostId id = PostId.newPostId();
        Title title = command.title();
        Content content = command.content();
        UserId authorId = new UserId(((AuthPrincipalDto) context).userId());

        Post post = new Post(id, title, content, authorId);
        postRepository.save(post);
        return id;
    }
}
