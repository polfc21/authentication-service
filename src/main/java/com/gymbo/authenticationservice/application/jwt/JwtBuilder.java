package com.gymbo.authenticationservice.application.jwt;

import com.gymbo.authenticationservice.domain.model.User;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class JwtBuilder {

    private final JwtKeyManager jwtKeyManager;

    @Value("${gymbo.jwt.issuer}")
    private String issuer;
    @Value("${gymbo.jwt.expire}")
    private int expire;

    public String buildToken(User user) {
        return Jwts
                .builder()
                .claims(new HashMap<>())
                .subject(user.getUsername())
                .issuer(this.issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.expire))
                .signWith(this.jwtKeyManager.getKey())
                .compact();
    }

}
