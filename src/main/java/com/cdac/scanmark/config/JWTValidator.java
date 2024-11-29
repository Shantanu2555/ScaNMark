package com.cdac.scanmark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JWTValidator {

    @Autowired
    private JWTProvider jwtProvider;

    // Validate token and extract username
    public boolean isValid(String token, String username) {
        return jwtProvider.validateToken(token, username);
    }
}

