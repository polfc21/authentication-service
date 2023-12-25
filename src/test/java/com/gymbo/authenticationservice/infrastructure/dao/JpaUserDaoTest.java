package com.gymbo.authenticationservice.infrastructure.dao;

import com.gymbo.authenticationservice.domain.model.User;
import com.gymbo.authenticationservice.infrastructure.repository.UserRepository;
import com.gymbo.authenticationservice.infrastructure.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class JpaUserDaoTest {

    @InjectMocks
    private JpaUserDao userDao;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    void givenExistentUsernameWhenFindByUsernameThenReturnUser() {
        String existentUsername = "existentUsername";
        UserEntity userEntity = mock(UserEntity.class);
        User user = mock(User.class);

        when(this.userRepository.findByUsername(existentUsername)).thenReturn(userEntity);
        when(this.mapper.map(userEntity, User.class)).thenReturn(user);

        assertThat(this.userDao.findByUsername(existentUsername), is(user));
    }

    @Test
    void givenNonExistentUsernameWhenFindByUsernameThenReturnNull() {
        String nonExistentUsername = "nonExistentUsername";

        when(this.userRepository.findByUsername(nonExistentUsername)).thenReturn(null);

        assertNull(this.userDao.findByUsername(nonExistentUsername));
    }
}
