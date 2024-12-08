package com.cdac.scanmark.service;

import com.cdac.scanmark.dto.AddFacultyRequest;
import com.cdac.scanmark.dto.JwtResponse;
import com.cdac.scanmark.dto.LoginRequest;
import com.cdac.scanmark.dto.OtpVerificationRequest;
import com.cdac.scanmark.entities.Faculty;
import java.util.List;
public interface FacultyService {
    List<Faculty> getAllFaculties();
    Faculty getFacultyByFacultyCode(String code);
    Faculty createFaculty(Faculty faculty);
    Faculty updateFaculty(String code, Faculty faculty);
    void deleteFaculty(String code);

    Faculty getFacultyByEmail(String email) ;

    Faculty addFaculty(AddFacultyRequest request) ;

    List<Faculty> getAllFaculty();

    JwtResponse signIn(LoginRequest loginRequest) ;

    void sendOtp(Faculty faculty) ;

    String verifyOtp(OtpVerificationRequest request) ;
}
