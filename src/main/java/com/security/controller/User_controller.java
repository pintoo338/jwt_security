package com.security.controller;

import com.security.entity.User;
import com.security.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/user")
public class User_controller {
@Autowired
    private Userservice userservice;


    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public List<User> getAllUsers() {
        return userservice.getAllUsers();
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userservice.saveUse(user);
    }
}
