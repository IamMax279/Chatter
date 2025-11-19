package com.max420.identity_service.infrastructure.adapters.in.controllers;

import com.max420.identity_service.application.commands.ChangeUsernameCommand;
import com.max420.identity_service.application.ports.in.ChangeUsernameUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final ChangeUsernameUseCase changeUsernameUseCase;

    public UserController(ChangeUsernameUseCase changeUsernameUseCase) {
        this.changeUsernameUseCase = changeUsernameUseCase;
    }

    @PostMapping("/change-username")
    public ResponseEntity<?> changeUsername(@RequestBody ChangeUsernameCommand command) {
        changeUsernameUseCase.execute(command);
        return ResponseEntity.ok("Your username has been changed successfully");
    }
}
