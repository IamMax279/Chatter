package com.max420.identity_service.application.ports.out;

import com.max420.identity_service.domain.models.user.Role;

import java.util.Set;

public interface JwtService {
    String generateToken(String email, Set<Role> roles);
    String extractEmail(String token);
    Set<Role> extractRoles(String token);
    boolean validateToken(String token);
}
