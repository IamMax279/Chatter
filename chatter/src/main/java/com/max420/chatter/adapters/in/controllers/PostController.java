package com.max420.chatter.adapters.in.controllers;

import com.max420.chatter.application.commands.post.CreatePostCommand;
import com.max420.chatter.application.ports.in.PostPort;
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
    public ResponseEntity<?> createPost(@RequestBody CreatePostCommand command) {
        postPort.createPost(command);
        return ResponseEntity.ok("Post created successfully");
    }
}
