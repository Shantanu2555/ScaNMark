package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @NonNull
    Optional<Lecture> findById(@NonNull Long id) ;
}
