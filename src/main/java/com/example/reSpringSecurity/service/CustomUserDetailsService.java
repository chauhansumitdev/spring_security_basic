package com.example.reSpringSecurity.service;

import com.example.reSpringSecurity.entity.User;
import com.example.reSpringSecurity.entity.UserPrincipal;
import com.example.reSpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);


        if(user == null){
            throw  new UsernameNotFoundException("USER DOES NOT EXIST");
        }else{
            return new UserPrincipal(user);
        }
    }
}
