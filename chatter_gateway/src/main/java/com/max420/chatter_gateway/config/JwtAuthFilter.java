package com.max420.chatter_gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
@Order(0)
public class JwtAuthFilter implements GlobalFilter {
    @Value("${jwt.secret.key}")
    private String secretKey;

    private static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/auth/sign-up",
            "/auth/sign-in"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();

        // if the path is a public endpoint, proceed to the next filter
        if (PUBLIC_ENDPOINTS.stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        // if there is no jwt token, terminate the flow
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            String token = authHeader.substring(7);
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            if (claims.getExpiration().before(new Date())) {
                throw new Exception("JWT expired.");
            }

            exchange = exchange.mutate()
                    .request(r -> r.headers(h -> h.add("X-User-email", claims.getSubject())))
                    .build();
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
