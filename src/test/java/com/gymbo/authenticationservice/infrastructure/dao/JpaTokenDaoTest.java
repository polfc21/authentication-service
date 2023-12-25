package com.gymbo.authenticationservice.infrastructure.dao;

import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.infrastructure.repository.TokenRepository;
import com.gymbo.authenticationservice.infrastructure.repository.entity.TokenEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaTokenDaoTest {

    @InjectMocks
    private JpaTokenDao tokenDao;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void givenTokenWhenCreateTokenThenReturnToken() {
        Token token = mock(Token.class);
        TokenEntity entityToSave = mock(TokenEntity.class);
        TokenEntity entitySaved = mock(TokenEntity.class);

        when(this.mapper.map(token, TokenEntity.class)).thenReturn(entityToSave);
        when(this.tokenRepository.save(entityToSave)).thenReturn(entitySaved);
        when(this.mapper.map(entitySaved, Token.class)).thenReturn(token);

        assertThat(this.tokenDao.save(token), is(token));
    }

    @Test
    void givenUserIdWhenFindAllByUserIdThenReturnTokenList() {
        Long userId = 1L;
        TokenEntity tokenEntity = mock(TokenEntity.class);
        Token token = mock(Token.class);

        when(this.tokenRepository.findAllByPlayerId(userId)).thenReturn(Set.of(tokenEntity));
        when(this.mapper.map(tokenEntity, Token.class)).thenReturn(token);

        assertThat(this.tokenDao.findAllByUserId(userId), is(Set.of(token)));
    }

    @Test
    void givenTokenListWhenSaveAllThenVerifySaveAll() {
        Token token = mock(Token.class);
        TokenEntity tokenEntity = mock(TokenEntity.class);

        when(this.mapper.map(token, TokenEntity.class)).thenReturn(tokenEntity);
        this.tokenDao.saveAll(Set.of(token));

        verify(this.tokenRepository).saveAll(Set.of(tokenEntity));
    }

}
