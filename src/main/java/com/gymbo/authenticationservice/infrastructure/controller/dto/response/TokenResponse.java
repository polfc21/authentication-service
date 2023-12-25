package com.gymbo.authenticationservice.infrastructure.controller.dto.response;

import com.gymbo.authenticationservice.domain.model.Token;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String token;
    public TokenResponse(Token token) {
        this.token = token.getToken();
    }
}
