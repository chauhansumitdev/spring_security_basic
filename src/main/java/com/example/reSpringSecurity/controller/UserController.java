package com.example.reSpringSecurity.controller;


import com.example.reSpringSecurity.dto.UserDTO;
import com.example.reSpringSecurity.entity.User;
import com.example.reSpringSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("PROTECTED END POINT : SUCCESS", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user){
        log.info( user.getUsername()+ " "+ user.getPassword() );
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
}
