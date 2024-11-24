package com.cdac.scanmark.controller;

import com.cdac.scanmark.entities.Lecture;
import com.cdac.scanmark.service.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
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
}
