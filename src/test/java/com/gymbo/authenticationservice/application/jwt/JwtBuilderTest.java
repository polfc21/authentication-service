package com.gymbo.authenticationservice.application.jwt;

import com.gymbo.authenticationservice.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class JwtBuilderTest {

    @Autowired
    private JwtBuilder jwtBuilder;

    @Mock
    private JwtKeyManager jwtKeyManager;

    @Value("${gymbo.jwt.secret}")
    private String secret;

    private static MockedStatic<Jwts> jwtsMockedStatic;

    @BeforeAll
    static void setUp() {
        jwtsMockedStatic = mockStatic(Jwts.class);
    }

    @AfterAll
    static void tearDown() {
        jwtsMockedStatic.close();
    }

    @Test
    void whenBuildTokenThenReturnToken() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        io.jsonwebtoken.JwtBuilder jwtBuilder = mock(io.jsonwebtoken.JwtBuilder.class);
        String expectedToken = "token";
        User user = mock(User.class);

        when(Jwts.builder()).thenReturn(jwtBuilder);
        when(jwtBuilder.claims(new HashMap<>())).thenReturn(jwtBuilder);
        when(jwtBuilder.subject(any())).thenReturn(jwtBuilder);
        when(jwtBuilder.issuer(any())).thenReturn(jwtBuilder);
        when(jwtBuilder.issuedAt(any())).thenReturn(jwtBuilder);
        when(jwtBuilder.expiration(any())).thenReturn(jwtBuilder);
        Mockito.when(this.jwtKeyManager.getKey()).thenReturn(key);
        when(jwtBuilder.signWith(key)).thenReturn(jwtBuilder);
        when(jwtBuilder.compact()).thenReturn(expectedToken);

        assertThat(this.jwtBuilder.buildToken(user), is(expectedToken));
    }
}