package com.max420.chatter.adapters.in.controllers;

import com.max420.chatter.adapters.in.dto.post.CreatePostRequest;
import com.max420.chatter.application.commands.post.CreatePostCommand;
import com.max420.chatter.application.ports.in.PostPort;
import com.max420.chatter.domain.models.post.Content;
import com.max420.chatter.domain.models.post.Title;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostPort postPort;

    public PostController(PostPort postPort) {
        this.postPort = postPort;
    }

    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest request) {
        postPort.createPost(new CreatePostCommand(
                new Title(request.title()),
                new Content(request.content())
        ));
        return ResponseEntity.ok("Post created successfully");
    }
}
