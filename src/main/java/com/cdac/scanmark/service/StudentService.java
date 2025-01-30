package com.cdac.scanmark.service;

import com.cdac.scanmark.dto.AddStudentRequest;
import com.cdac.scanmark.dto.LoginRequest;
import com.cdac.scanmark.dto.OtpVerificationRequest;
import com.cdac.scanmark.entities.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    Student getStudentByPrn(Long prn);

    Student getStudentByEmail(String email);

    Student addStudent(AddStudentRequest request);

    Object signIn(LoginRequest loginRequest);

    void sendOtp(Student student);

    String verifyOtp(OtpVerificationRequest request);
}
