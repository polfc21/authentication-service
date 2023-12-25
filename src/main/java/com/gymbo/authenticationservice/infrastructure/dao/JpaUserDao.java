package com.gymbo.authenticationservice.infrastructure.dao;

import com.gymbo.authenticationservice.domain.dao.UserDao;
import com.gymbo.authenticationservice.domain.model.User;
import com.gymbo.authenticationservice.infrastructure.repository.UserRepository;
import com.gymbo.authenticationservice.infrastructure.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaUserDao implements UserDao {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public User findByUsername(String username) {
        UserEntity entity = this.userRepository.findByUsername(username);
        return entity != null ? this.mapper.map(entity, User.class) : null;
    }

}
