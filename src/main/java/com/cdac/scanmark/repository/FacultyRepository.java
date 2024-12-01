package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String> {

    @NonNull
    Optional<Faculty> findById(@NonNull String id) ;

    Optional<Faculty> findByEmail(String email) ;

    boolean existsByEmail(String email) ;

}
