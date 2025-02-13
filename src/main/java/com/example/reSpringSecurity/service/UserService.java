package com.example.reSpringSecurity.service;


import com.example.reSpringSecurity.dto.UserDTO;
import com.example.reSpringSecurity.entity.User;
import com.example.reSpringSecurity.mapper.UserMapper;
import com.example.reSpringSecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService {



    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }


    public UserDTO registerUser(UserDTO user){

        log.info(user.getUsername()+ " "+ user.getPassword());

        User getUser = userMapper.mapToEntity(user);
        getUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info(getUser.getPassword());
         User currentUser = userRepository.save(getUser);

         return userMapper.mapToDTO(currentUser);
    }
}
