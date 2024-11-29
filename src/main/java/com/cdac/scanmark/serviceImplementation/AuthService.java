package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.dto.LoginRequest;
import com.cdac.scanmark.dto.LoginResponse;
import com.cdac.scanmark.entities.Coordinator;
import com.cdac.scanmark.entities.Faculty;
import com.cdac.scanmark.entities.Passwords;
import com.cdac.scanmark.entities.Student;
import com.cdac.scanmark.repository.CoordinatorRepository;
import com.cdac.scanmark.repository.FacultyRepository;
import com.cdac.scanmark.repository.StudentRepository;
import com.cdac.scanmark.repository.PasswordsRepository;
import com.cdac.scanmark.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordsRepository passwordsRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthService(StudentRepository studentRepository, FacultyRepository facultyRepository,
                       CoordinatorRepository coordinatorRepository, PasswordEncoder passwordEncoder,
                       PasswordsRepository passwordsRepository, JwtUtil jwtUtil) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.coordinatorRepository = coordinatorRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordsRepository = passwordsRepository;
        this.jwtUtil = jwtUtil;
    }

    private String findPassword(String role, Object identifier) {
        switch (role.toLowerCase()) {
            case "student" -> {
                if (identifier instanceof Long) {
                    return passwordsRepository.findPasswordByStudentPrn((Long) identifier)
                            .orElseThrow(() -> new RuntimeException("Password not found for Student PRN: " + identifier)); // Extract the password
                } else {
                    throw new RuntimeException("Invalid identifier type for student role");
                }
            }

            case "faculty" -> {
                if (identifier instanceof String) {
                    return passwordsRepository.findPasswordByFacultyCode((String) identifier)
                            .orElseThrow(() -> new RuntimeException("Password not found for Faculty Code: " + identifier)); // Extract the password
                } else {
                    throw new RuntimeException("Invalid identifier type for faculty role");
                }
            }

            case "coordinator" -> {
                if (identifier instanceof Long) {
                    return passwordsRepository.findPasswordByCoordinatorId((Long) identifier)
                            .orElseThrow(() -> new RuntimeException("Password not found for Coordinator ID: " + identifier)); // Extract the password
                } else {
                    throw new RuntimeException("Invalid identifier type for coordinator role");
                }
            }

            default -> throw new RuntimeException("Invalid role for password lookup");
        }
    }



    public LoginResponse login(LoginRequest loginRequest) {
        String role = loginRequest.getRole();
        String token;
        String message;

        // Deciding which identifier to pass
        Object identifier;
        if ("student".equalsIgnoreCase(role)) {
            identifier = loginRequest.getPrnOrId();  // For Student, it's the PRN (Long)
        } else if ("faculty".equalsIgnoreCase(role)) {
            identifier = loginRequest.getFacultyCode();  // For Faculty, it's the Faculty Code (String)
        } else if ("coordinator".equalsIgnoreCase(role)) {
            identifier = loginRequest.getPrnOrId();  // For Coordinator, it's the ID (Long)
        } else {
            throw new RuntimeException("Invalid Role");
        }

        // Fetch the hashed password based on the role and identifier
        String hashedPassword = findPassword(role, identifier);

        switch (role.toLowerCase()) {
            case "student" -> {
                Student student = studentRepository.findByPrn(loginRequest.getPrnOrId())
                        .orElseThrow(() -> new RuntimeException("Invalid Student PRN"));
                if (!passwordEncoder.matches(loginRequest.getPassword(), hashedPassword)) {
                    throw new RuntimeException("Invalid Password");
                }
                token = jwtUtil.generateToken(student.getPrn().toString());
                message = "Login successful for Student: " + student.getName();
                return new LoginResponse(token, message);
            }
            case "faculty" -> {
                Faculty faculty = facultyRepository.findById(loginRequest.getFacultyCode())
                        .orElseThrow(() -> new RuntimeException("Invalid Faculty Code"));
                if (!passwordEncoder.matches(loginRequest.getPassword(), hashedPassword)) {
                    throw new RuntimeException("Invalid Password");
                }
                token = jwtUtil.generateToken(faculty.getFacultyCode());
                message = "Login successful for Faculty: " + faculty.getName();
                return new LoginResponse(token, message);
            }
            case "coordinator" -> {
                Coordinator coordinator = coordinatorRepository.findById(loginRequest.getPrnOrId())
                        .orElseThrow(() -> new RuntimeException("Invalid Coordinator ID"));
                if (!passwordEncoder.matches(loginRequest.getPassword(), hashedPassword)) {
                    throw new RuntimeException("Invalid Password");
                }
                token = jwtUtil.generateToken(coordinator.getId().toString());
                message = "Login successful for Coordinator: " + coordinator.getName();
                return new LoginResponse(token, message);
            }
            default -> throw new RuntimeException("Invalid Role");
        }
    }
}



