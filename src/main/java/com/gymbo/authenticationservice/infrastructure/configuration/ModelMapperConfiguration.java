package com.gymbo.authenticationservice.infrastructure.configuration;

import com.gymbo.authenticationservice.domain.model.Token;
import com.gymbo.authenticationservice.infrastructure.repository.entity.TokenEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(TokenEntity.class, Token.class)
                .addMapping(TokenEntity::getPlayer, Token::setUser);
        modelMapper.typeMap(Token.class, TokenEntity.class)
                .addMapping(Token::getUser, TokenEntity::setPlayer);
        return modelMapper;
    }

}


