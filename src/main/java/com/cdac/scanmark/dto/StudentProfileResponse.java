package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentProfileResponse {
    private Long prn ;
    private String email ;
    private String macAddress ;
    private String name ;

    public StudentProfileResponse(Long prn, String email, String macAddress, String name) {
        this.prn = prn;
        this.email = email;
        this.macAddress = macAddress;
        this.name = name;
    }

    public StudentProfileResponse(){}
}
