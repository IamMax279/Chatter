package com.max420.chatter.infrastructure.adapters.in.security;

import com.max420.chatter.application.ports.out.JwtService;
import com.max420.chatter.domain.models.user.Role;
import com.max420.chatter.infrastructure.adapters.in.dto.AuthPrincipalDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Value("${jwt.secret.key}")
    private String jwtSecret;

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/auth/sign-up") || request.getServletPath().equals("/auth/sign-in");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Long userId = jwtService.extractUserId(token);
                String email = jwtService.extractEmail(token);
                Set<Role> roles = jwtService.extractRoles(token);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                new AuthPrincipalDto(userId, email),
                                null,
                                // TODO: implement custom UserDetails/DTO
                                roles.stream().map(Role::toString).map(SimpleGrantedAuthority::new).toList());

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // do not set any auth
            }
        }

        filterChain.doFilter(request, response);
    }
}
