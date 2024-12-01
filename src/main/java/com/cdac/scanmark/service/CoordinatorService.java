package com.cdac.scanmark.service;

import com.cdac.scanmark.dto.*;
import com.cdac.scanmark.entities.Coordinator;

import java.util.List;

public interface CoordinatorService {

    // Get all coordinators
    List<Coordinator> getAllCoordinators();

    // Get coordinator by id
    Coordinator getCoordinatorById(Long id);

    // Create a new coordinator
    Coordinator createCoordinator(Coordinator coordinator);

    // Update coordinator details
    Coordinator updateCoordinator(Long id, Coordinator coordinator);

    // Delete coordinator
    void deleteCoordinator(Long id);

    SignUpResponse signup(SignUpRequest signUpRequest) ;

    String verifyOtp(OtpVerificationRequest request) ;

    JwtResponse signIn(LoginRequest loginRequest) ;

    Coordinator getCoordinatorByEmail(String email);
}
