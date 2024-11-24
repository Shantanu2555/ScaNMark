package com.cdac.scanmark.entities;

import jakarta.persistence.* ;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @Column(unique = true, nullable = false)
    private Long prn ;
    private String name ;
    private String email ;
    @Column(nullable = false)
    private String macAddress ;

    public Student(){

    }
    public Student(Integer id, Long prn, String name, String email, String macAddress) {
        this.id = id;
        this.prn = prn;
        this.name = name;
        this.email = email;
        this.macAddress = macAddress;
    }

    public Integer getId() {
        return id;
    }

    public Long getPrn() {
        return prn;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrn(Long prn) {
        this.prn = prn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
