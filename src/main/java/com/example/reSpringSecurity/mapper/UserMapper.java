package com.example.reSpringSecurity.mapper;

import com.example.reSpringSecurity.dto.UserDTO;
import com.example.reSpringSecurity.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public UserMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public UserDTO mapToDTO(User user){
        return objectMapper.convertValue(user, UserDTO.class);
    }

    public User mapToEntity(UserDTO userDTO){
        return objectMapper.convertValue(userDTO, User.class);
    }
}
