package com.gymbo.authenticationservice.application.service;

import com.gymbo.authenticationservice.application.domain.UserDetailsImpl;
import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.domain.service.AuthenticationService;
import com.gymbo.authenticationservice.domain.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public Token login(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticationResult = this.authenticationManager.authenticate(authentication);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authenticationResult.getPrincipal();
        return this.tokenService.createToken(userDetailsImpl.getUser());
    }

}
