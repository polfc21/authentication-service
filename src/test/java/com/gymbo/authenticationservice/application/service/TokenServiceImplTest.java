package com.gymbo.authenticationservice.application.service;

import com.gymbo.authenticationservice.application.jwt.JwtBuilder;
import com.gymbo.authenticationservice.domain.dao.TokenDao;
import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private TokenDao tokenDao;

    @Mock
    private JwtBuilder jwtBuilder;

    @Test
    void givenTokenAndUserTokensIsEmptyWhenCreateTokenThenReturnToken() {
        User user = mock(User.class);
        String tokenString = "token";
        Token token = mock(Token.class);

        when(user.getId()).thenReturn(1L);
        when(this.tokenDao.findAllByUserId(user.getId())).thenReturn(Set.of());
        when(this.jwtBuilder.buildToken(user)).thenReturn(tokenString);
        when(this.tokenDao.save(any(Token.class))).thenReturn(token);

        verify(this.tokenDao, times(0)).saveAll(anySet());
        assertThat(this.tokenService.createToken(user), is(token));
    }

    @Test
    void givenTokenAndUserTokensIsNotEmptyWhenCreateTokenThenReturnToken() {
        User user = mock(User.class);
        String tokenString = "token";
        Token token = mock(Token.class);

        when(user.getId()).thenReturn(1L);
        when(this.tokenDao.findAllByUserId(user.getId())).thenReturn(Set.of(token));
        when(this.jwtBuilder.buildToken(user)).thenReturn(tokenString);
        when(this.tokenDao.save(any(Token.class))).thenReturn(token);

        Token result = this.tokenService.createToken(user);

        verify(this.tokenDao, times(1)).saveAll(Set.of(token));
        assertThat(result, is(token));
    }

}
