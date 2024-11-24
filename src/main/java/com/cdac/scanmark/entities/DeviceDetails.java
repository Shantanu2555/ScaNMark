package com.cdac.scanmark.entities;

import jakarta.persistence.*;

@Entity
public class DeviceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String macAddress;

    @Column(nullable = false)
    private String deviceName;

    public DeviceDetails(Long id, Student student, String macAddress, String deviceName) {
        this.id = id;
        this.student = student;
        this.macAddress = macAddress;
        this.deviceName = deviceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
