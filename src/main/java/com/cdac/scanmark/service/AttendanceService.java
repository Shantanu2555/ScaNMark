package com.cdac.scanmark.service;

import com.cdac.scanmark.entities.Attendance;
import java.util.List;


public interface AttendanceService {
    List<Attendance> getAllAttendance();
    Attendance getAttendanceById(Long id);
    Attendance createAttendance(Attendance attendance);
    Attendance updateAttendance(Long id, Attendance attendance);
    void deleteAttendance(Long id);

    List<Attendance> getAttendanceByStudent(Long studentId);

    List<Attendance> getAttendanceByLecture(Long lectureId);
}
