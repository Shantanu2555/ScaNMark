package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional ;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Additional query methods can go here
    Optional<Student> findByPrn(Long prn);

    Optional<Student> findByEmail(String email) ;

    boolean existsByEmail(String email) ;
}
