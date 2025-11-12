package com.max420.identity_service.infrastructure.adapters.in.controllers;

import com.max420.identity_service.application.commands.RegisterUserCommand;
import com.max420.identity_service.application.ports.in.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserCommand command) {
        registerUserUseCase.execute(command);
        return ResponseEntity.ok("success");
    }
}
