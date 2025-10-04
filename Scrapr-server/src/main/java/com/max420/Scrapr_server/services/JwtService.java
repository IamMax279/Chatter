package com.max420.Scrapr_server.services;

import javax.crypto.SecretKey;

public interface JwtService {
    SecretKey getKey(String key);
    String generateToken(String email);
}
