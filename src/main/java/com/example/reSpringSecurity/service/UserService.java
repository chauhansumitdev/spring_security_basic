package com.example.reSpringSecurity.service;


import com.example.reSpringSecurity.entity.User;
import com.example.reSpringSecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Lazy
@Slf4j
@Service
public class UserService {



    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public User registerUser(User user){
        UUID id= UUID.randomUUID();
        user.setId(id);

        log.info("" + user.getId()+ " "+ user.getUsername()+ " "+ user.getPassword());

        return userRepository.save(user);
    }

}
