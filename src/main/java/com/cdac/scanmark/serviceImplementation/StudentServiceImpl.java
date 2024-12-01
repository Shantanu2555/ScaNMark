package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.Student;
import com.cdac.scanmark.repository.StudentRepository;
import com.cdac.scanmark.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByPrn(Long prn) {
        return studentRepository.findByPrn(prn)
                .orElseThrow(() -> new RuntimeException("Student not found with PRN: " + prn));
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + email));
    }

    @Override
    public Student createStudent(Student student) {
        if (studentRepository.findByPrn(student.getPrn()).isPresent()) {
            throw new RuntimeException("Student with PRN already exists: " + student.getPrn());
        }
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long prn, Student updatedStudent) {
        Student existingStudent = getStudentByPrn(prn);
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setMacAddress(updatedStudent.getMacAddress());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long prn) {
        Student student = getStudentByPrn(prn);
        studentRepository.delete(student);
    }
}
