package com.cdac.scanmark.controller;

import com.cdac.scanmark.dto.LoginRequest;
import com.cdac.scanmark.dto.LoginResponse;
import com.cdac.scanmark.entities.Student;
import com.cdac.scanmark.service.StudentService;
import com.cdac.scanmark.serviceImplementation.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final AuthService authService;

    public StudentController(StudentService studentService, AuthService authService) {
        this.studentService = studentService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{prn}")
    public ResponseEntity<Student> getStudentByPrn(@PathVariable Long prn) {
        return ResponseEntity.ok(studentService.getStudentByPrn(prn));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping("/{prn}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long prn, @RequestBody Student updatedStudent) {
        return ResponseEntity.ok(studentService.updateStudent(prn, updatedStudent));
    }

    @DeleteMapping("/{prn}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long prn) {
        studentService.deleteStudent(prn);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        loginRequest.setRole("student");
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
