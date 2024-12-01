package com.cdac.scanmark.service;

import com.cdac.scanmark.entities.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student createStudent(Student student);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);

    Student getStudentByPrn(Long prn) ;

    Student getStudentByEmail(String email) ;
}
