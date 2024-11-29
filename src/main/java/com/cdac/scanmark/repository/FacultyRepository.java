package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, String> {

    @NonNull
    Optional<Faculty> findById(@NonNull String id) ;

}
