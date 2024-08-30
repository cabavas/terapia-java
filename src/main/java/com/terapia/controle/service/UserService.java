package com.terapia.controle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String plainPassword) {
        String encodedPassword = passwordEncoder.encode(plainPassword);
    }

    public void updateUserPassword(Long userId, String newPlainPassword) {
        String encodedPassword = passwordEncoder.encode(newPlainPassword);
    }

    public String getPasswordByUsername(String username) {
        //CHANGE LOGIC
        return username;
    }
}
