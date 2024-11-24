package com.cdac.scanmark.service;

import com.cdac.scanmark.entities.Faculty;
import java.util.List;
public interface FacultyService {
    List<Faculty> getAllFaculties();
    Faculty getFacultyById(Long id);
    Faculty createFaculty(Faculty faculty);
    Faculty updateFaculty(Long id, Faculty faculty);
    void deleteFaculty(Long id);

    List<Faculty> getAllFaculty();
}
