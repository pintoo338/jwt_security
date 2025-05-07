package com.security.service;

import com.security.dao.UserRepository;
import com.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userserviceimpl implements Userservice {

 @Autowired
 private UserRepository userRepository;
@Autowired
 private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        return null;
    }

    /*  @Override
        public User saveUser(User user) {
            return userRepository.save(user);
        }
    */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUse(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


}
