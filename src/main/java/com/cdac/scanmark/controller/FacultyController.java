package com.cdac.scanmark.controller;

import com.cdac.scanmark.config.JWTProvider;
import com.cdac.scanmark.dto.*;
import com.cdac.scanmark.entities.Faculty;
import com.cdac.scanmark.entities.QRData;
import com.cdac.scanmark.repository.QRDataRepository;
import com.cdac.scanmark.service.FacultyService;
import com.cdac.scanmark.service.ForgotPasswordService;
import com.cdac.scanmark.serviceImplementation.AuthService;
import com.cdac.scanmark.util.JwtUtil;
import com.cdac.scanmark.util.QRCodeGenerator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final FacultyService facultyService;
    private final AuthService authService;
    private final JWTProvider jwtProvider;
    private final ForgotPasswordService forgotPasswordService;
    private final QRDataRepository qrDataRepository;

    public FacultyController(
            FacultyService facultyService, QRDataRepository qrDataRepository, AuthService authService,
            JWTProvider jwtProvider,
            ForgotPasswordService forgotPasswordService) {
        this.facultyService = facultyService;
        this.qrDataRepository = qrDataRepository;
        this.authService = authService;
        this.jwtProvider = jwtProvider;
        this.forgotPasswordService = forgotPasswordService;
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable String code) {
        return ResponseEntity.ok(facultyService.getFacultyByFacultyCode(code));
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.createFaculty(faculty));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable String code, @RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.updateFaculty(code, faculty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String code) {
        facultyService.deleteFaculty(code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        loginRequest.setRole("faculty");
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<FacultyProfileResponse> getStudentProfile(@RequestHeader("Authorization") String token) {
        // Extract the email from the JWT token
        String emailFromToken = jwtProvider.getUsernameFromToken(token.substring(7)); // Remove "Bearer "

        // Fetch user (can be Coordinator, Faculty, or Student)
        Faculty faculty = facultyService.getFacultyByEmail(emailFromToken);

        // Map to DTO and return
        FacultyProfileResponse response = new FacultyProfileResponse();
        response.setDepartment(faculty.getDepartment());
        response.setName(faculty.getName());
        response.setEmail(faculty.getEmail());
        response.setFacultyCode(faculty.getFacultyCode());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(
            @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        String email = forgotPasswordRequest.getEmail();
        String responseMessage = forgotPasswordService.forgotPassword(email);

        ForgotPasswordResponse response = new ForgotPasswordResponse();
        response.setMessage(responseMessage);
        response.setSuccess(!responseMessage.contains("failed"));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        String email = resetPasswordRequest.getEmail();
        String otp = resetPasswordRequest.getOtp();
        String newPassword = resetPasswordRequest.getNewPassword();
        String response = forgotPasswordService.resetPassword(email, otp, newPassword);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/generate-qr")
    public ResponseEntity<QRResponse> generateQRCode(
            @RequestBody LocationRequest locationRequest,
            @RequestParam Long lectureId,
            @RequestHeader("Authorization") String token) {
        try {
            QRResponse response = facultyService.generateQRForSession(locationRequest, token.substring(7), lectureId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new QRResponse(e.getMessage()));
        }
    }

    @GetMapping("/show-qr-again")
    public ResponseEntity<?> showQRCodeAgain(@RequestParam Long lectureId) {
        QRData latestQR = qrDataRepository.findTopByLectureIdOrderByCreatedAtDesc(lectureId);
        if (latestQR != null) {
            return ResponseEntity.ok(Collections.singletonMap("qrCode", latestQR.getQrDataBase64()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "No QR Code found for this lecture"));
        }
    }

}
