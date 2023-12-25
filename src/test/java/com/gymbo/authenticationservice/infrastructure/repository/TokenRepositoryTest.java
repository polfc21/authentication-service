package com.gymbo.authenticationservice.infrastructure.repository;

import com.gymbo.authenticationservice.infrastructure.repository.entity.TokenEntity;
import com.gymbo.authenticationservice.infrastructure.repository.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class TokenRepositoryTest {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TestEntityManager entityManager;

    private final TokenEntity token = TokenEntity.builder().build();
    private final UserEntity player = UserEntity.builder().build();

    @BeforeEach
    void setUp() {
        this.entityManager.persist(this.player);
        this.token.setPlayer(this.player);
        this.entityManager.persist(this.token);
    }

    @AfterEach
    void tearDown() {
        this.entityManager.remove(this.token);
        this.entityManager.remove(this.player);
    }

    @Test
    void givenPlayerIdIsPresentWhenFindAllByPlayerIdThenReturnTokenList() {
        Set<TokenEntity> tokens = this.tokenRepository.findAllByPlayerId(this.player.getId());

        assertThat(tokens, hasItem(this.token));
    }

    @Test
    void givenPlayerIdIsNotPresentWhenFindAllByPlayerIdThenReturnEmptyTokenList() {
        Set<TokenEntity> tokens = this.tokenRepository.findAllByPlayerId(this.player.getId() - 1);

        assertThat(tokens.size(), is(0));
    }

}
