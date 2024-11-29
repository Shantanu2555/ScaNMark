package com.cdac.scanmark.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_prn", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    @Column(nullable = false)
    private Boolean isPresent;

    public Attendance(Long id, Student student, Lecture lecture, Boolean isPresent) {
        this.id = id;
        this.student = student;
        this.lecture = lecture;
        this.isPresent = isPresent;
    }

    public Attendance(){}

}
