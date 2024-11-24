package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {}

