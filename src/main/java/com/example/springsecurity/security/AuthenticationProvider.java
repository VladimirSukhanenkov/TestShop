//package com.example.springsecurity.security;
//
//import com.example.springsecurity.services.PersonDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.AuthenticationException;
////import org.springframework.stereotype.Component;
////
////import org.springframework.security.core.Authentication;
////import org.springframework.stereotype.Component;
//
//@Component
//public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        // Получаем логин с формы. Spring Security сам создаст объект формы и передаст его в объект authentication
//        String login = authentication.getName();
////        return authentication;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
