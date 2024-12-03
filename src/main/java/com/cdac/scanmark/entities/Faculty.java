package com.cdac.scanmark.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Faculty {

    @Id
    @Column(nullable = false, unique = true)
    private String facultyCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String department;

    public Faculty(String facultyCode, String name, String email, String department) {
        this.facultyCode = facultyCode;
        this.name = name;
        this.email = email;
        this.department = department;
    }
    public Faculty(){}

}
