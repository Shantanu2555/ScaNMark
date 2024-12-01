package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
    private String role ;

    public LoginRequest(){}

    public LoginRequest(String email, String password, String role){
        this.email = email ;
        this.password = password ;
        this.role = role ;
    }


}

