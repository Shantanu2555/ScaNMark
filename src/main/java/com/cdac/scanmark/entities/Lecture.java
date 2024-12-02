package com.cdac.scanmark.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(nullable = false)
    private String facultyName ;

    @Column(nullable = false)
    private String subjectName ;

    @Column(nullable = false)
    private LocalDateTime lectureTime ;

    @Column(nullable = false)
    private String qrcodeReferance ;

    public Lecture(){

    }

    public Lecture(Integer id, String facultyName, String subjectName, LocalDateTime lectureTime, String qrcodeReferance) {
        this.id = id;
        this.facultyName = facultyName;
        this.subjectName = subjectName;
        this.lectureTime = lectureTime;
        this.qrcodeReferance = qrcodeReferance;
    }
}
