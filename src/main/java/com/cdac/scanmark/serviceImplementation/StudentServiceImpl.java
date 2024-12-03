package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.dto.AddStudentRequest;
import com.cdac.scanmark.entities.Passwords;
import com.cdac.scanmark.entities.Student;
import com.cdac.scanmark.repository.PasswordsRepository;
import com.cdac.scanmark.repository.StudentRepository;
import com.cdac.scanmark.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder ;
    private final PasswordsRepository passwordsRepository ;

    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder, PasswordsRepository passwordsRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordsRepository = passwordsRepository;
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

    @Override
    public Student addStudent(AddStudentRequest request) {
        if (studentRepository.existsById(request.getPrn())) {
            throw new RuntimeException("Student with PRN " + request.getPrn() + " already exists.");
        }

        // Save Student
        Student student = new Student();
        student.setPrn(request.getPrn());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setMacAddress(request.getMacAddress());
        studentRepository.save(student);

        // Generate default or custom password
        String defaultPassword = String
                .valueOf(request.getPrn())
                .substring(String.valueOf(request.getPrn())
                .length() - 4); // Last 4 digits of PRN


        String encodedPassword = passwordEncoder.encode(defaultPassword) ;

        // Save Password
        Passwords passwordEntry = new Passwords();
        passwordEntry.setStudent(student); // Set foreign key to Student
        passwordEntry.setPassword(encodedPassword);
        passwordsRepository.save(passwordEntry);

        return student;
    }
}
