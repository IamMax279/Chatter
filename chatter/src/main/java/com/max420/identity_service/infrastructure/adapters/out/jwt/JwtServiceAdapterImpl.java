package com.max420.identity_service.infrastructure.adapters.out.jwt;

import com.max420.identity_service.application.ports.out.JwtService;
import com.max420.identity_service.domain.models.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtServiceAdapterImpl implements JwtService {
    @Value("${jwt.secret.key}")
    private String jwtSecret;

    public SecretKey getKey(String key) {
        byte[] bytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }

    @Override
    public String generateToken(String email, Set<Role> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", email);
        claims.put("roles", roles);
        claims.put("iat", new Date(System.currentTimeMillis()));
        claims.put("exp", new Date(System.currentTimeMillis() + 1000 * 60 * 15));

        return Jwts.builder()
                .claims(claims)
                .signWith(getKey(jwtSecret))
                .compact();
    }

    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Set<Role> extractRoles(String token) {
        return extractClaim(token, claims -> {
            Object rolesObj = claims.get("roles");
            if (rolesObj instanceof List<?>) {
                return ((List<?>) rolesObj).stream()
                        .map(Object::toString)
                        .map(Role::valueOf)
                        .collect(Collectors.toSet());
            }
            return Collections.emptySet();
        });
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(getKey(jwtSecret)).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey(jwtSecret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
