package com.gymbo.authenticationservice.application.jwt;

import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Component;

@Component
public class JwtDecoder {

    public byte[] getKeyBytes(String secret) {
        return Decoders.BASE64.decode(secret);
    }

}

