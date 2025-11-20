package com.max420.identity_service.infrastructure.adapters.in.controllers;

import com.max420.identity_service.application.commands.ChangeBioCommand;
import com.max420.identity_service.application.commands.ChangeUsernameCommand;
import com.max420.identity_service.application.ports.in.ChangeBioUseCase;
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
    private final ChangeBioUseCase changeBioUseCase;

    public UserController(
            ChangeUsernameUseCase changeUsernameUseCase,
            ChangeBioUseCase changeBioUseCase
    ) {
        this.changeUsernameUseCase = changeUsernameUseCase;
        this.changeBioUseCase = changeBioUseCase;
    }

    @PostMapping("/change-username")
    public ResponseEntity<?> changeUsername(@RequestBody ChangeUsernameCommand command) {
        changeUsernameUseCase.execute(command);
        return ResponseEntity.ok("Your username has been changed successfully");
    }

    @PostMapping("/change-bio")
    public ResponseEntity<?> changeBio(@RequestBody ChangeBioCommand command) {
        changeBioUseCase.execute(command);
        return ResponseEntity.ok("Your bio has been changed successfully");
    }
}
