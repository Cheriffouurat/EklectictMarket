package com.example.eklecticproject.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {
    private BCryptPasswordEncoder passwordEncoder;
    private String rawPassword;

    public PasswordEncoderService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        this.rawPassword = rawPassword;
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
