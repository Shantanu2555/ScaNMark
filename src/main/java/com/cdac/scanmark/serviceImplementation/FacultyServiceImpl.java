package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.Faculty;
import com.cdac.scanmark.repository.FacultyRepository;
import com.cdac.scanmark.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
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
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();  // Fetch all faculties
    }
}
