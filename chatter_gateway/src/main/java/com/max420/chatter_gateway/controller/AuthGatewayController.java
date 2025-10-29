package com.max420.chatter_gateway.controller;

import com.max420.chatter_gateway.dto.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthGatewayController {
    private final WebClient authWebClient;
    private final WebClient userWebClient;

    public AuthGatewayController(WebClient authWebClient, WebClient userWebClient) {
        this.authWebClient = authWebClient;
        this.userWebClient = userWebClient;
    }

    @PostMapping("/sign-up")
    public Mono<ResponseEntity<?>> registerUser(@RequestBody UserRegistrationRequest request) {
        return authWebClient.post()
                .uri("/auth/sign-up")
                .bodyValue(Map.of("email", request.getEmail(), "password", request.getPassword()))
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(authResponse -> {
                    // String userId = (String) authResponse.get("userId");

                    return userWebClient.post()
                            .uri("/user/test")
                            .bodyValue(Map.of("username", request.getUserName()))
                            .retrieve()
                            .bodyToMono(Map.class)
                            .map(userResponse -> {
                                Map<String, Object> result = new HashMap<>();
                                result.put("auth", authResponse);
                                result.put("profile", userResponse);
                                return ResponseEntity.ok(result);
                            });
                });
    }
}
