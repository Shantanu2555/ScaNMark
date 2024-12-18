package com.cdac.scanmark.entities;

import java.time.LocalDateTime;

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
    private Boolean isVerified ;

    //for otp based authentication
    @Column(nullable = true)
    private String otp ;

    @Column(nullable = true)
    private LocalDateTime otpExpiration ;

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
    public Boolean getIsVerified(){
        return isVerified ;
    }
    public void setIsVerified(Boolean value){
        this.isVerified = value ;
    }

}
