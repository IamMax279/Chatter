package com.max420.Scrapr_server.controllers;

import com.max420.Scrapr_server.dto.user.UserRegistrationDto;
import com.max420.Scrapr_server.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationDto userDto) {
        try {
            userService.createUser(userDto);
            return ResponseEntity.ok("Successfully created a new user.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
