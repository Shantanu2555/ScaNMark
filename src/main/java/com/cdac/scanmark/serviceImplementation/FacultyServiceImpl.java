package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.Faculty;
import com.cdac.scanmark.repository.FacultyRepository;
import com.cdac.scanmark.service.FacultyService;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return null;
    }

    @Override
    public Faculty getFacultyById(Long id) {
        return null;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return null;
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        return null;
    }

    @Override
    public void deleteFaculty(Long id) {

    }

    @Override
    public List<Faculty> getAllFaculty() {
        return null;
    }
}

