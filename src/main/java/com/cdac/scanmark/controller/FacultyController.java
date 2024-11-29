package com.cdac.scanmark.controller;

import com.cdac.scanmark.dto.LoginRequest;
import com.cdac.scanmark.dto.LoginResponse;
import com.cdac.scanmark.entities.Faculty;
import com.cdac.scanmark.service.FacultyService;
import com.cdac.scanmark.serviceImplementation.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService facultyService;
    private final AuthService authService;

    public FacultyController(FacultyService facultyService, AuthService authService) {
        this.facultyService = facultyService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable String code) {
        return ResponseEntity.ok(facultyService.getFacultyByFacultyCode(code));
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.createFaculty(faculty));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable String code, @RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.updateFaculty(code, faculty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String code) {
        facultyService.deleteFaculty(code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        loginRequest.setRole("faculty");
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
