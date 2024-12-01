package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Attendance;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @NonNull
        // Find attendance by studentId
    List<Attendance> findByStudentPrn(Long prn);

    // Find attendance by lectureId
    List<Attendance> findByLectureId(Long lectureId);
}
