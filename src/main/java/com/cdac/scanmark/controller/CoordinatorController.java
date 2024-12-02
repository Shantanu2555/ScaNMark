package com.cdac.scanmark.controller;

import com.cdac.scanmark.config.JWTProvider;
import com.cdac.scanmark.dto.*;
import com.cdac.scanmark.entities.Attendance;
import com.cdac.scanmark.exceptions.ResourceNotFoundException;
import com.cdac.scanmark.service.AttendanceService;
import com.cdac.scanmark.service.CoordinatorService;
import com.cdac.scanmark.service.ForgotPasswordService;
import com.cdac.scanmark.entities.Coordinator ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/coordinators")
public class CoordinatorController {

    @Autowired
    private final CoordinatorService coordinatorService ;
    @Autowired
    private JWTProvider jwtProvider ;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private AttendanceService attendanceService ;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        loginRequest.setRole("coordinator");
        // Call signIn method in CoordinatorService
        JwtResponse jwtResponse = coordinatorService.signIn(loginRequest);

        // Map JwtResponse to LoginResponse
        LoginResponse loginResponse = new LoginResponse(jwtResponse.getToken(), jwtResponse.getMessage());  // call signIn here
        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody SignUpRequest signUpRequest){
        SignUpResponse response = coordinatorService.signup(signUpRequest) ;
        return ResponseEntity.ok(response) ;
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        String response = coordinatorService.verifyOtp(request);
        return ResponseEntity.ok(response);
    }

    // Forgot Password - Request OTP
    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        String email = forgotPasswordRequest.getEmail();
        String responseMessage = forgotPasswordService.forgotPassword(email);

        ForgotPasswordResponse response = new ForgotPasswordResponse();
        response.setMessage(responseMessage);
        response.setSuccess(!responseMessage.contains("failed"));

        return ResponseEntity.ok(response);
    }

    // Reset Password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        String email = resetPasswordRequest.getEmail();
        String otp = resetPasswordRequest.getOtp();
        String newPassword = resetPasswordRequest.getNewPassword();
        String response = forgotPasswordService.resetPassword(email, otp, newPassword);
        return ResponseEntity.ok(response);
    }

    // Get Profile (Only authorized users can access their profile)
    @GetMapping("/profile")
    public ResponseEntity<CoordinatorProfileResponse> getUserProfile(@RequestHeader("Authorization") String token) {
        // Extract the email from the JWT token
        String emailFromToken = jwtProvider.getUsernameFromToken(token.substring(7)); // Remove "Bearer "

        // Fetch user (can be Coordinator, Faculty, or Student)
        Coordinator coordinator = coordinatorService.getCoordinatorByEmail(emailFromToken);

        // Map to DTO and return
        CoordinatorProfileResponse response = new CoordinatorProfileResponse();
        response.setName(coordinator.getName());
        response.setEmail(coordinator.getEmail());

        return ResponseEntity.ok(response);
    }
    @GetMapping("/search-student/{prn}")
    public ResponseEntity<StudentHistoryResponse> searchStudent(@PathVariable Long prn){
        StudentHistoryResponse response = coordinatorService.getStudentHistoryByPrn(prn) ;
        return ResponseEntity.ok(response) ;
    }

    @GetMapping("/search-attendance-by-date/{date}")
    public ResponseEntity<List<Attendance>> getAttendanceByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByDate(date);
        if (attendanceList.isEmpty()) {
            throw new ResourceNotFoundException("No attendance records found for the given date: " + date);
        }
        return ResponseEntity.ok(attendanceList);
    }
}
