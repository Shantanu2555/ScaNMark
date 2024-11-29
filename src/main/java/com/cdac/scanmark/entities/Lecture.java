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
    private Integer facultyName ;

    @Column(nullable = false)
    private String subjectName ;

    @Column(nullable = false)
    private LocalDateTime lectureTime ;

    @Column(nullable = false)
    private Long qrcodeReferance ;

    public Lecture(){

    }
    public Lecture(Integer id, Integer facultyName, String subjectName, LocalDateTime lectureTime, Long qrcodeReferance) {
        this.id = id;
        this.facultyName = facultyName;
        this.subjectName = subjectName;
        this.lectureTime = lectureTime;
        this.qrcodeReferance = qrcodeReferance;
    }

}
