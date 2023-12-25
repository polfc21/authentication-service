package com.gymbo.authenticationservice.domain.service;

import com.gymbo.authenticationservice.domain.model.Token;

public interface AuthenticationService {
    Token login(String username, String password);
}
