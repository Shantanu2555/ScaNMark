package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.config.JWTProvider;
import com.cdac.scanmark.dto.*;
import com.cdac.scanmark.entities.Coordinator;
import com.cdac.scanmark.entities.Passwords;
import com.cdac.scanmark.repository.CoordinatorRepository;
import com.cdac.scanmark.repository.PasswordsRepository;
import com.cdac.scanmark.service.CoordinatorService;
import com.cdac.scanmark.service.MailSenderService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoordinatorServiceImpl implements CoordinatorService {

    private final CoordinatorRepository coordinatorRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordsRepository passwordsRepository ;
    private final MailSenderService mailSenderService ;
    private final JWTProvider jwtProvider ;

    public CoordinatorServiceImpl(CoordinatorRepository coordinatorRepository, PasswordEncoder passwordEncoder, PasswordsRepository passwordsRepository, MailSenderService mailSenderService, JWTProvider jwtProvider) {
        this.coordinatorRepository = coordinatorRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordsRepository = passwordsRepository;
        this.mailSenderService = mailSenderService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    @Override
    public Coordinator getCoordinatorById(Long id) {
        return coordinatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinator not found with id " + id));
    }

    @Override
    public Coordinator createCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    @Override
    public Coordinator updateCoordinator(Long id, Coordinator coordinator) {
        Coordinator existingCoordinator = coordinatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinator not found with id " + id));
        existingCoordinator.setName(coordinator.getName());
        // Update other fields as necessary
        return coordinatorRepository.save(existingCoordinator);
    }

    @Override
    public void deleteCoordinator(Long id) {
        coordinatorRepository.deleteById(id);
    }

    @Transactional
    @Override
    public SignUpResponse signup(SignUpRequest signUpRequest) {
        if (coordinatorRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        String encodePassword = passwordEncoder.encode(signUpRequest.getPassword());

        // Create and save Coordinator entity
        Coordinator coordinator = new Coordinator();
        coordinator.setEmail(signUpRequest.getEmail());
        coordinator.setName(signUpRequest.getName());

        // Generate OTP and expiration
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit OTP
        coordinator.setOtp(otp);
        coordinator.setOtpExpiration(LocalDateTime.now().plusMinutes(10)); // OTP valid for 10 minutes

        Coordinator savedCoordinator = coordinatorRepository.save(coordinator);

        // Save password in Passwords entity
        Passwords passwords = new Passwords();
        passwords.setCoordinator(savedCoordinator);
        passwords.setPassword(encodePassword);
        passwordsRepository.save(passwords);

        // Send OTP email (logic to be implemented in mail service)
        mailSenderService.sendOtp(signUpRequest.getEmail(), otp);

        return new SignUpResponse("Coordinator registered successfully. Please verify OTP to activate your account.", coordinator.getId());
    }

    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        String email = loginRequest.getEmail(); // Get email from login request

        // Fetch the coordinator by email
        Coordinator coordinator = coordinatorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Coordinator not found"));

        // Check if the coordinator is verified
        if (!coordinator.getIsVerified()) {
            throw new RuntimeException("Coordinator is not verified. Please complete OTP verification.");
        }

        // Fetch the password by coordinator ID
        String password = passwordsRepository.findByCoordinatorId(coordinator.getId())
                .map(Passwords::getPassword)
                .orElseThrow(() -> new RuntimeException("Password not found for coordinator"));

        // Validate password
        if (!passwordEncoder.matches(loginRequest.getPassword(), password)) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT token with email as the subject
        String token = jwtProvider.generateToken(email, "ROLE_COORDINATOR");

        // Return JwtResponse with token and a success message
        return new JwtResponse(token, "Login successful");
    }

    @Transactional
    @Override
    public String verifyOtp(OtpVerificationRequest request) {
        // Fetch the coordinator by ID
        Coordinator coordinator = coordinatorRepository.findById(request.getCoordinatorId())
                .orElseThrow(() -> new RuntimeException("Coordinator not found"));

        // Check if the OTP matches and is not expired
        if (!coordinator.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }
        if (coordinator.getOtpExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP has expired");
        }

        // Mark coordinator as verified
        coordinator.setIsVerified(true);
        coordinator.setOtp(null); // Clear OTP after successful verification
        coordinator.setOtpExpiration(null); // Clear expiration time
        coordinatorRepository.save(coordinator);

        return "Coordinator verified successfully";
    }

    @Override
    public Coordinator getCoordinatorByEmail(String email) {
        return coordinatorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Coordinator not found with email: " + email));
    }


}
