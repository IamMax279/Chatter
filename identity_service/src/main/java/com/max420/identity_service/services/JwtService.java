package com.max420.identity_service.services;

import javax.crypto.SecretKey;

public interface JwtService {
    SecretKey getKey(String key);
    String generateToken(String email);
    boolean validateToken(String token);
    String extractEmail(String token);
}
