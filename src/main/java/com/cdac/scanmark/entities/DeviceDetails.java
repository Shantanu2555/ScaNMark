package com.cdac.scanmark.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DeviceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_prn", nullable = false)
    private Student student;

    @Column(nullable = false, unique = true)
    private String macAddress;

    @Column(nullable = false)
    private String deviceName;

    public DeviceDetails(Long id, Student student, String macAddress, String deviceName) {
        this.id = id;
        this.student = student;
        this.macAddress = macAddress;
        this.deviceName = deviceName;
    }
    public DeviceDetails(){}
}
