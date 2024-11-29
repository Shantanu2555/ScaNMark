package com.cdac.scanmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String message;

    // Constructor
    public JwtResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


