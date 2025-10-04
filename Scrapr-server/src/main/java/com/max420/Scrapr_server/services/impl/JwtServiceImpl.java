package com.max420.Scrapr_server.services.impl;

import com.max420.Scrapr_server.services.JwtService;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret.key}")
    private String key;

    @Override
    public SecretKey getKey(String key) {
        byte[] bytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }

    @Override
    public String generateToken(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .setSubject(email)
                .signWith(getKey(key))
                .compact();
    }
}
