package com.cdac.scanmark.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FacultyProfileResponse {
    private String facultyCode ;
    private String department ;
    private String email ;
    private String name ;

    public FacultyProfileResponse(String facultyCode, String department, String email, String name) {
        this.facultyCode = facultyCode;
        this.department = department;
        this.email = email;
        this.name = name;
    }

    public FacultyProfileResponse(){}
}
