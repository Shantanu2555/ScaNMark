package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional ;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Additional query methods can go here
    Optional<Student> findByPrn(Long prn);
}
