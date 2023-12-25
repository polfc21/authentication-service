package com.gymbo.authenticationservice.application.service;

import com.gymbo.authenticationservice.application.domain.UserDetailsImpl;
import com.gymbo.authenticationservice.domain.dao.UserDao;
import com.gymbo.authenticationservice.domain.model.User;
import com.gymbo.authenticationservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = this.userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
        return new UserDetailsImpl(user);
    }

}
