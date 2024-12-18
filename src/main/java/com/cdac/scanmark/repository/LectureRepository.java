package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Faculty;
import com.cdac.scanmark.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @NonNull
    Optional<Lecture> findById(@NonNull Long id) ;

    @Query("SELECT l FROM Lecture l WHERE l.faculty.facultyCode = :facultyCode")
    List<Lecture> findByFacultyCode(@Param("facultyCode") String facultyCode);

    List<Lecture> findByFaculty(Faculty faculty);
}
