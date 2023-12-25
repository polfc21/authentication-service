package com.gymbo.authenticationservice.application.service;

import com.gymbo.authenticationservice.application.jwt.JwtBuilder;
import com.gymbo.authenticationservice.domain.dao.TokenDao;
import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.domain.model.User;
import com.gymbo.authenticationservice.domain.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenDao tokenDao;
    private final JwtBuilder jwtBuilder;

    @Override
    public Token createToken(User user) {
        this.revokeAllTokens(user);
        String tokenString = this.jwtBuilder.buildToken(user);
        Token token = new Token(tokenString);
        token.setUser(user);
        return this.tokenDao.save(token);
    }

    private void revokeAllTokens(User user) {
        Set<Token> tokens = this.tokenDao.findAllByUserId(user.getId());
        if (!tokens.isEmpty()) {
            tokens.forEach(token -> {
                token.setIsRevoked(true);
                token.setIsExpired(true);
            });
            this.tokenDao.saveAll(tokens);
        }
    }

}
