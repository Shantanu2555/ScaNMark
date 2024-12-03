package com.cdac.scanmark.entities;

import jakarta.persistence.* ;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student {

    @Id
    private Long prn ;

    @Column(nullable = false)
    private String name ;

    @Column(unique = true, nullable = false)
    private String email ;

    @Column(nullable = false)
    private String macAddress ;

    @Column(nullable = false)
    private boolean isVerified ;

    public Student(){
        isVerified = false ;
    }
    public Student(Long prn, String name, String email, String macAddress) {
        this.prn = prn;
        this.name = name;
        this.email = email;
        this.macAddress = macAddress;
        isVerified = false ;
    }

}
