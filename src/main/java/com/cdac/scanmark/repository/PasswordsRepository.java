package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Passwords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordsRepository extends JpaRepository<Passwords, Long> {

    @Query("SELECT p.password FROM Passwords p WHERE p.student.prn = :prn")
    Optional<String> findPasswordByStudentPrn(Long prn);

    @Query("SELECT p.password FROM Passwords p WHERE p.faculty.facultyCode = :facultyCode")
    Optional<String> findPasswordByFacultyCode(String facultyCode);

    @Query("SELECT p.password FROM Passwords p WHERE p.coordinator.id = :coordinatorId")
    Optional<String> findPasswordByCoordinatorId(Long coordinatorId);

    @Query("SELECT p FROM Passwords p WHERE p.coordinator.id = " +
            "(SELECT c.id FROM Coordinator c WHERE c.email = :email)")
    Passwords findByCoordinatorEmail(@Param("email") String email);

}
