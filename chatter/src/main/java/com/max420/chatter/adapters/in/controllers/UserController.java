package com.max420.chatter.adapters.in.controllers;

import com.max420.chatter.application.commands.user.ChangeBioCommand;
import com.max420.chatter.application.commands.user.ChangeUsernameCommand;
import com.max420.chatter.application.ports.in.UserPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserPort userPort;

    public UserController(UserPort userPort) {
        this.userPort = userPort;
    }

    @PostMapping("/change-username")
    public ResponseEntity<?> changeUsername(@RequestBody ChangeUsernameCommand command) {
        userPort.changeUsername(command);
        return ResponseEntity.ok("Your username has been changed successfully");
    }

    @PostMapping("/change-bio")
    public ResponseEntity<?> changeBio(@RequestBody ChangeBioCommand command) {
        userPort.changeBio(command);
        return ResponseEntity.ok("Your bio has been changed successfully");
    }
}
