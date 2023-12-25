package com.gymbo.authenticationservice.infrastructure.controller;

import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.domain.service.AuthenticationService;
import com.gymbo.authenticationservice.infrastructure.controller.constant.ApiConstant;
import com.gymbo.authenticationservice.infrastructure.controller.dto.request.LoginRequest;
import com.gymbo.authenticationservice.infrastructure.controller.dto.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApiConstant.API + ApiConstant.V1 + ApiConstant.LOGIN)
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        Token token = this.authenticationService.login(request.getUsername(), request.getPassword());
        return new ResponseEntity<>(new TokenResponse(token), HttpStatus.OK);
    }

}
