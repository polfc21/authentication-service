package com.gymbo.authenticationservice.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.domain.service.AuthenticationService;
import com.gymbo.authenticationservice.infrastructure.controller.constant.ApiConstant;
import com.gymbo.authenticationservice.infrastructure.controller.dto.request.LoginRequest;
import com.gymbo.authenticationservice.infrastructure.controller.dto.response.TokenResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenValidLoginRequestWhenLoginThenReturnTokenAnd200() throws Exception {
        LoginRequest loginRequest = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();

        Token token = new Token("dummyToken");
        TokenResponse tokenResponse = new TokenResponse(token);

        given(this.authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword())).willReturn(token);

        MockHttpServletResponse response = this.mockMvc.perform(
                post(ApiConstant.API + ApiConstant.V1 + ApiConstant.LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(loginRequest))
        ).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        assertThat(response.getContentAsString(), is(this.objectMapper.writeValueAsString(tokenResponse)));
    }

    @Test
    void givenInvalidLoginRequestWhenLoginThenReturn403() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("incorrect_password");

        given(this.authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword())).willThrow(BadCredentialsException.class);

        MockHttpServletResponse response = mockMvc.perform(
                post(ApiConstant.API + ApiConstant.V1 + ApiConstant.LOGIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(loginRequest))
        ).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.FORBIDDEN.value()));
    }

}