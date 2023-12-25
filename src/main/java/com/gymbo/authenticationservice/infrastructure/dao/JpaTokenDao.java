package com.gymbo.authenticationservice.infrastructure.dao;

import com.gymbo.authenticationservice.domain.dao.TokenDao;
import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.infrastructure.repository.TokenRepository;
import com.gymbo.authenticationservice.infrastructure.repository.entity.TokenEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JpaTokenDao implements TokenDao {

    private final TokenRepository tokenRepository;
    private final ModelMapper mapper;

    @Override
    public Token save(Token token) {
        TokenEntity entityToSave = this.mapper.map(token, TokenEntity.class);
        TokenEntity entitySaved = this.tokenRepository.save(entityToSave);
        return this.mapper.map(entitySaved, Token.class);
    }

    @Override
    public Set<Token> findAllByUserId(Long userId) {
        return this.tokenRepository.findAllByPlayerId(userId).stream()
                .map(tokenEntity -> this.mapper.map(tokenEntity, Token.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void saveAll(Set<Token> tokens) {
        Set<TokenEntity> entities = tokens.stream()
                .map(token -> this.mapper.map(token, TokenEntity.class))
                .collect(Collectors.toSet());
        this.tokenRepository.saveAll(entities);
    }

}
