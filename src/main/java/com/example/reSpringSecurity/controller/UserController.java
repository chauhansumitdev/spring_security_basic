package com.example.reSpringSecurity.controller;


import com.example.reSpringSecurity.entity.User;
import com.example.reSpringSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@Slf4j
@RestController
@RequestMapping("api/v1/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        log.info(user.getId()+ " "+ user.getUsername()+ " "+ user.getPassword() );
        return userService.registerUser(user);
    }
}
