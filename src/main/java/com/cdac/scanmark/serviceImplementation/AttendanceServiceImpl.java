package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.Attendance;
import com.cdac.scanmark.repository.AttendanceRepository;
import com.cdac.scanmark.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return null;
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return null;
    }

    @Override
    public Attendance createAttendance(Attendance attendance) {
        return null;
    }

    @Override
    public Attendance updateAttendance(Long id, Attendance attendance) {
        return null;
    }

    @Override
    public void deleteAttendance(Long id) {

    }

    @Override
    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return null;
    }

    @Override
    public List<Attendance> getAttendanceByLecture(Long lectureId) {
        return null;
    }
}

