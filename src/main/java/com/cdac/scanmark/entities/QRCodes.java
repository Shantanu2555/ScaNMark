package com.cdac.scanmark.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class QRCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String qrData;

    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    public QRCodes(Long id, String qrData, Lecture lecture, LocalDateTime generatedAt, LocalDateTime expiresAt) {
        this.id = id;
        this.qrData = qrData;
        this.lecture = lecture;
        this.generatedAt = generatedAt;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
