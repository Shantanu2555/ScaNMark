package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    // Additional custom query methods
}
