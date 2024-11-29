package com.cdac.scanmark.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class GeoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_prn", nullable = false)
    private Student student;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private LocalDateTime scannedAt;

    public GeoLog(Long id, Student student, Double latitude, Double longitude, LocalDateTime scannedAt) {
        this.id = id;
        this.student = student;
        this.latitude = latitude;
        this.longitude = longitude;
        this.scannedAt = scannedAt;
    }

    public GeoLog(){}
}
