package com.cdac.scanmark.controller;

import com.cdac.scanmark.dto.*;
import com.cdac.scanmark.service.CoordinatorService;
import com.cdac.scanmark.service.ForgotPasswordService;
import com.cdac.scanmark.serviceImplementation.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coordinators")
public class CoordinatorController {
    private final AuthService authService;
    private final CoordinatorService coordinatorService ;

    @Autowired
    private ForgotPasswordService forgotPasswordService;


    public CoordinatorController(AuthService authService, CoordinatorService coordinatorService) {

        this.authService = authService;
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


}
