package com.gymbo.authenticationservice.infrastructure.repository;

import com.gymbo.authenticationservice.infrastructure.repository.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    Set<TokenEntity> findAllByPlayerId(Long playerId);
}
