package com.gymbo.authenticationservice.application.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@RequiredArgsConstructor
public class JwtKeyManager {

    @Value("${gymbo.jwt.secret}")
    private String secret;

    private final JwtDecoder jwtDecoder;

    public Key getKey() {
        byte[] keyBytes = this.jwtDecoder.getKeyBytes(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
