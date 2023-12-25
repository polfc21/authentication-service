package com.gymbo.authenticationservice.application.service;

import com.gymbo.authenticationservice.application.domain.UserDetailsImpl;
import com.gymbo.authenticationservice.domain.dao.UserDao;
import com.gymbo.authenticationservice.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    @Test
    void givenExistentUsernameWhenLoadUserByUsernameThenReturnUserDetails() {
        String existentUsername = "existentUsername";
        User user = mock(User.class);

        when(this.userDao.findByUsername(existentUsername)).thenReturn(user);

        assertThat(this.userService.loadUserByUsername(existentUsername), is(new UserDetailsImpl(user)));
    }

    @Test
    void givenNonExistentUsernameWhenLoadUserByUsernameThenThrowUsernameNotFoundException() {
        String nonExistentUsername = "nonExistentUsername";

        when(this.userDao.findByUsername(nonExistentUsername)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> this.userService.loadUserByUsername(nonExistentUsername));
    }

}
