package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
public class LoginRequest {
    private Long prnOrId;
    private String password;
    private String facultyCode ;
    private String role ;

    public LoginRequest(){}

    public LoginRequest(Long prnOrId, String password, String facultyCode, String role){
        this.prnOrId = prnOrId ;
        this.password = password ;
        this.facultyCode = facultyCode ;
        this.role = role ;
    }


}

