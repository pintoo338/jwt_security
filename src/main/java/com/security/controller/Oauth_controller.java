package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home/oauth")
public class Oauth_controller {

    @GetMapping
    public String greet() {
         return "hello oauth";
    }


}
