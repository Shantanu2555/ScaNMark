package com.cdac.scanmark.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(Integer facultyName) {
        this.facultyName = facultyName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public LocalDateTime getLectureTime() {
        return lectureTime;
    }

    public void setLectureTime(LocalDateTime lectureTime) {
        this.lectureTime = lectureTime;
    }

    public Long getQrcodeReferance() {
        return qrcodeReferance;
    }

    public void setQrcodeReferance(Long qrcodeReferance) {
        this.qrcodeReferance = qrcodeReferance;
    }
}
