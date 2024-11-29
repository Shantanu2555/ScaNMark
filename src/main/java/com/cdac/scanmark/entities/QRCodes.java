package com.cdac.scanmark.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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

    public QRCodes(){}
}
