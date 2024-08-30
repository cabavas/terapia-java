package com.terapia.controle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public boolean authenticate(String username, String password) {
        String encodedPassword = userService.getPasswordByUsername(username);
        return passwordEncoder.matches(password, encodedPassword);
    }
}
