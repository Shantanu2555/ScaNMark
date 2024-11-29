package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.Passwords;
import com.cdac.scanmark.repository.CoordinatorRepository;
import com.cdac.scanmark.repository.PasswordsRepository;
import com.cdac.scanmark.service.ForgotPasswordService;
import com.cdac.scanmark.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private MailSenderService mailSenderService; // Reusing the MailSenderService
    @Autowired
    private CoordinatorRepository coordinatorRepository;
    @Autowired
    private PasswordsRepository passwordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    private static final int OTP_LENGTH = 6;
    private static final long OTP_EXPIRATION_TIME = 300000; // 5 minutes for OTP expiry

    // Temporary storage for OTPs (Use Redis or a DB table for production)
    private final Map<String, OtpData> otpStorage = new HashMap<>();

    @Override
    public String forgotPassword(String email) {
        // Check if the email exists in the Coordinator table
        if (!coordinatorRepository.existsByEmail(email)) {
            return "Email not found.";
        }

        // Generate OTP
        String otp = generateOtp();

        // Send OTP email
        boolean emailSent = sendOtpEmail(email, otp);

        if (emailSent) {
            // Store OTP for validation
            storeOtp(email, otp);
            return "OTP sent to your email.";
        } else {
            return "Failed to send OTP. Please try again.";
        }
    }

    @Override
    public String resetPassword(String email, String otp, String newPassword) {
        // Validate OTP
        OtpData otpData = otpStorage.get(email);
        if (otpData == null || !otpData.getOtp().equals(otp)) {
            return "Invalid OTP.";
        }
        if (System.currentTimeMillis() - otpData.getTimestamp() > OTP_EXPIRATION_TIME) {
            otpStorage.remove(email); // Remove expired OTP
            return "OTP has expired. Please request a new one.";
        }

        String hashedNewPassword = passwordEncoder.encode(newPassword);
        // Update the password
        Passwords passwordRecord = passwordRepository.findByCoordinatorEmail(email);
        if (passwordRecord != null) {
            passwordRecord.setPassword(hashedNewPassword); // Set the new password  hashed
            passwordRepository.save(passwordRecord);
            otpStorage.remove(email); // Clear OTP after successful password reset
            return "Password updated successfully.";
        } else {
            return "No password record found for this email.";
        }
    }

    // Helper method to generate OTP
    private String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10)); // Generate a random digit for OTP
        }
        return otp.toString();
    }

    // Helper method to send OTP via email
    private boolean sendOtpEmail(String toEmail, String otp) {
        try {
            mailSenderService.sendOtp(toEmail, otp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Method to store OTP with a timestamp
    private void storeOtp(String email, String otp) {
        otpStorage.put(email, new OtpData(otp, System.currentTimeMillis()));
    }

    // Inner class to hold OTP and timestamp
    private static class OtpData {
        private final String otp;
        private final long timestamp;

        public OtpData(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }

        public String getOtp() {
            return otp;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
