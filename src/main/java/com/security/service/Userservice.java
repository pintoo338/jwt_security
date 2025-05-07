package com.security.service;

import com.security.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Userservice {
    User saveUser(User user);

    List<User> getAllUsers();

    User saveUse(User user);
}
