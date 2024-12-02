package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.Attendance;
import com.cdac.scanmark.repository.AttendanceRepository;
import com.cdac.scanmark.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();  // Fetch all attendance records
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        Optional<Attendance> attendance = attendanceRepository.findById(id);  // Find attendance by ID
        return attendance.orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));
    }

    @Override
    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);  // Save the attendance record
    }

    @Override
    public Attendance updateAttendance(Long id, Attendance attendance) {
        Attendance existingAttendance = getAttendanceById(id);  // Fetch existing attendance record
        existingAttendance.setIsPresent(attendance.getIsPresent());  // Update attendance status
        return attendanceRepository.save(existingAttendance);  // Save the updated record
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = getAttendanceById(id);  // Fetch attendance record by ID
        attendanceRepository.delete(attendance);  // Delete attendance record
    }

    @Override
    public List<Attendance> getAttendanceByStudent(Long prn) {
        return attendanceRepository.findByStudentPrn(prn);  // Fetch attendance records by student ID
    }

    @Override
    public List<Attendance> getAttendanceByLecture(Long lectureId) {
        return attendanceRepository.findByLectureId(lectureId);  // Fetch attendance records by lecture ID
    }

    @Override
    // Method to get attendance for a specific LocalDate
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        // Convert LocalDate to LocalDateTime (start of day and end of day)
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1); // 1 day after startOfDay

        // Fetch the attendance records for the given date range
        return attendanceRepository.findByLectureDateBetween(startOfDay, endOfDay);
    }


}
