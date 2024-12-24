package com.cdac.scanmark.service;

import java.time.LocalDate;
import java.util.List;

import com.cdac.scanmark.dto.AttendanceRequest;
import com.cdac.scanmark.entities.Attendance;

import jakarta.validation.ValidationException;


public interface AttendanceService {

    List<Attendance> getAllAttendance();

    Attendance getAttendanceById(Long id);

    Attendance createAttendance(Attendance attendance);

    Attendance updateAttendance(Long id, Attendance attendance);

    void deleteAttendance(Long id);

    List<Attendance> getAttendanceByStudent(Long studentId);

    List<Attendance> getAttendanceByLecture(Long lectureId);

    List<Attendance> getAttendanceByDate(LocalDate date) ;

    void markAttendance(AttendanceRequest request) ;

}
