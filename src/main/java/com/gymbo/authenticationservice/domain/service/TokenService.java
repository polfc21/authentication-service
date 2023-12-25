package com.gymbo.authenticationservice.domain.service;

import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.domain.model.User;

public interface TokenService {
    Token createToken(User user);
}
