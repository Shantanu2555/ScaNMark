package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
