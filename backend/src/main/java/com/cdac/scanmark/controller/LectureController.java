package com.cdac.scanmark.controller;

import com.cdac.scanmark.entities.Faculty;
import com.cdac.scanmark.entities.Lecture;
import com.cdac.scanmark.repository.FacultyRepository;
import com.cdac.scanmark.repository.LectureRepository;
import com.cdac.scanmark.service.LectureService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;
    private final LectureRepository lectureRepository;
    private final FacultyRepository facultyRepository;

    public LectureController(FacultyRepository facultyRepository, LectureService lectureService,
            LectureRepository lectureRepository) {
        this.lectureService = lectureService;
        this.lectureRepository = lectureRepository;
        this.facultyRepository = facultyRepository;
    }

    @GetMapping
    public ResponseEntity<List<Lecture>> getAllLectures() {
        return ResponseEntity.ok(lectureService.getAllLectures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable Long id) {
        return ResponseEntity.ok(lectureService.getLectureById(id));
    }

    @PostMapping
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
        return ResponseEntity.ok(lectureService.createLecture(lecture));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable Long id, @RequestBody Lecture lecture) {
        return ResponseEntity.ok(lectureService.updateLecture(id, lecture));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }

    // getting list of lectures by faculty name
    @GetMapping("/lectures")
    public ResponseEntity<List<Lecture>> getLecturesByFacultyName(@RequestHeader("Authorization") String token,
            @RequestParam String facultyName) {
        try {
            List<Lecture> lectures = lectureService.getFreshLecturesByFaculty(facultyName);

            if (lectures.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(lectures);
        } catch (Exception e) {
            System.err.println("Error while processing the request: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not fetch lectures");
        }
    }

}
