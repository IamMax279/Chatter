package com.max420.chatter.infrastructure.adapters.in.controllers;

import com.max420.chatter.application.commands.auth.LoginCommand;
import com.max420.chatter.application.commands.auth.RegisterUserCommand;
import com.max420.chatter.application.ports.in.auth.LoginUseCase;
import com.max420.chatter.application.ports.in.auth.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUseCase loginUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserCommand command) {
        registerUserUseCase.execute(command);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginCommand command) {
        String token = loginUseCase.execute(command);
        return ResponseEntity.ok(token);
    }
}
