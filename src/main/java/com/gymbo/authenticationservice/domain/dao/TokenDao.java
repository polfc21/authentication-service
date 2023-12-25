package com.gymbo.authenticationservice.domain.dao;

import com.gymbo.authenticationservice.domain.model.Token;

import java.util.Set;

public interface TokenDao {
    Token save(Token token);
    Set<Token> findAllByUserId(Long userId);
    void saveAll(Set<Token> tokens);
}
