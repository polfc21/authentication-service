package com.gymbo.authenticationservice.domain.dao;

import com.gymbo.authenticationservice.domain.model.User;

public interface UserDao {
    User findByUsername(String username);
}
