package com.example.reSpringSecurity.filter;

import com.example.reSpringSecurity.service.CustomUserDetailsService;
import com.example.reSpringSecurity.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.context.ApplicationContext;

import java.io.IOException;


@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final ApplicationContext applicationContext;

    @Autowired
    public JwtFilter(JwtService jwtService, ApplicationContext applicationContext){
        this.jwtService = jwtService;
        this.applicationContext = applicationContext;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String extractToken = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if(extractToken != null && extractToken.startsWith("Bearer")){
            log.info("COMPLETE TOKEN" + extractToken);

            token = extractToken.substring(7);

            log.info(" EXTRACTED TOKEN "+ token);

            username = jwtService.extractUserName(token);



            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){


                UserDetails userDetails = applicationContext.getBean(CustomUserDetailsService.class).loadUserByUsername(username);

                if(jwtService.validateToken(token, userDetails)){

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            }


        }

        filterChain.doFilter(request, response);
    }
}
