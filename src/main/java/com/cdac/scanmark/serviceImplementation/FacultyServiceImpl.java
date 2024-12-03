package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.dto.AddFacultyRequest;
import com.cdac.scanmark.entities.Faculty;
import com.cdac.scanmark.entities.Passwords;
import com.cdac.scanmark.repository.FacultyRepository;
import com.cdac.scanmark.repository.PasswordsRepository;
import com.cdac.scanmark.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final PasswordEncoder passwordEncoder ;
    private final PasswordsRepository passwordsRepository  ;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, PasswordEncoder passwordEncoder, PasswordsRepository passwordsRepository) {
        this.facultyRepository = facultyRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordsRepository = passwordsRepository;
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();  // Fetch all faculty members
    }

    @Override
    public Faculty getFacultyByFacultyCode(String code) {
        Optional<Faculty> faculty = facultyRepository.findById(code);  // Find faculty by faculty code
        return faculty.orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + code));
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);  // Save new faculty record
    }

    @Override
    public Faculty updateFaculty(String code, Faculty faculty) {
        Faculty existingFaculty = getFacultyByFacultyCode(code);  // Fetch existing faculty
        existingFaculty.setName(faculty.getName());  // Update faculty details
        return facultyRepository.save(existingFaculty);  // Save updated faculty record
    }

    @Override
    public void deleteFaculty(String code) {
        Faculty faculty = getFacultyByFacultyCode(code);  // Fetch faculty by ID
        facultyRepository.delete(faculty);  // Delete faculty record
    }

    @Override
    public Faculty getFacultyByEmail(String email) {
        return facultyRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Faculty not found with email: " + email));
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();  // Fetch all faculties
    }

    @Override
    public Faculty addFaculty(AddFacultyRequest request) {
        if (facultyRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Faculty with email " + request.getEmail() + " already exists.");
        }

        // Save Faculty
        Faculty faculty = new Faculty();
        faculty.setFacultyCode(request.getFacultyCode());
        faculty.setDepartment(request.getDepartment());
        faculty.setEmail(request.getEmail());
        faculty.setName(request.getName());
        facultyRepository.save(faculty);

        // Generate default or custom password
        String defaultPassword = (request.getName() == null || request.getName().isBlank())
                ? "defaultPassword"  // Use a default password when name is missing or blank
                : request.getName().trim().toLowerCase().substring(0, request.getName().trim().indexOf(" ") == -1 ? request.getName().length() : request.getName().indexOf(" "));


        String encodedPassword = passwordEncoder.encode(defaultPassword) ;

        // Save Password
        Passwords passwordEntry = new Passwords();
        passwordEntry.setFaculty(faculty); // Set foreign key to Student
        passwordEntry.setPassword(encodedPassword);
        passwordsRepository.save(passwordEntry);

        return faculty;
    }
}
