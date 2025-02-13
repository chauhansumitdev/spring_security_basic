package com.example.reSpringSecurity.config;


import com.example.reSpringSecurity.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MiscConfig {

    @Lazy
    @Bean
    public UserMapper userMapper(ObjectMapper objectMapper){
        return new UserMapper(objectMapper);
    }


    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
