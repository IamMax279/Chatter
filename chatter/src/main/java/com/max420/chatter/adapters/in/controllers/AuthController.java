package com.max420.chatter.adapters.in.controllers;

import com.max420.chatter.adapters.in.dto.user.RegisterUserRequest;
import com.max420.chatter.adapters.in.dto.user.SignInRequest;
import com.max420.chatter.application.commands.auth.LoginCommand;
import com.max420.chatter.application.commands.auth.RegisterUserCommand;
import com.max420.chatter.application.ports.in.AuthPort;
import com.max420.chatter.domain.models.user.Email;
import com.max420.chatter.domain.models.user.Password;
import com.max420.chatter.domain.models.user.Username;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthPort authPort;

    public AuthController(AuthPort authPort) {
        this.authPort = authPort;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserRequest request) {
        authPort.register(new RegisterUserCommand(
                new Email(request.email()),
                new Password(request.password()),
                new Username(request.username())
        ));
        return ResponseEntity.ok("success");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        String token = authPort.login(new LoginCommand(
                new Email(request.email()),
                new Password(request.password())
        ));
        return ResponseEntity.ok(token);
    }
}
