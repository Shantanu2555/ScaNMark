package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
    private String token;
    private String message;

    public LoginResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public LoginResponse() {
    }
}
