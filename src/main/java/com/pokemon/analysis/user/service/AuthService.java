package com.pokemon.analysis.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.validation.ValidationException;

import com.pokemon.analysis.user.model.entity.User;
import com.pokemon.analysis.user.model.enums.Role;
import com.pokemon.analysis.user.repository.UserRepository;
import com.pokemon.analysis.user.dto.request.LoginRequestDTO;
import com.pokemon.analysis.user.dto.request.SignupRequestDTO;
import com.pokemon.analysis.user.dto.request.VerifyUserDTO;
import com.pokemon.analysis.user.dto.response.UserResponseDTO;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    @Transactional
    public UserResponseDTO signup(SignupRequestDTO signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new ValidationException("Username already exists");
        }
        
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new ValidationException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(false);
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(5));
        user.setCreatedAt(LocalDateTime.now());

        sendVerificationEmail(user);
        

        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
            .id(savedUser.getId())
            .username((savedUser.getUsername()))
            .email(savedUser.getEmail())
            .role(savedUser.getRole())
            .message("Signed up successfully")
            .build();
    }


    @Transactional
    public User authenticate(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account is not verified. Please verifiy your account.");
        }

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        return user;
    }



    public void verifyUser(VerifyUserDTO verifyDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(verifyDTO.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired");
            }

            if (user.getVerificationCode().equals(verifyDTO.getVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);
                userRepository.save(user);

            } else {
                throw new RuntimeException("Invalid verification code");
            }

        } else {
            throw new RuntimeException("User not found");
        }
    } 


    public void resendVerificationCode(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.isEnabled()) {
                throw new RuntimeException("Account is already verified");
            }

            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiresAt(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            userRepository.save(user);

        } else {
            throw new RuntimeException("User not found");
        }
    }



    public void sendVerificationEmail(User user) {
        String subject = "Account verification";
        String verificationCode = user.getVerificationCode();
        String htmlMessage = "<html>"
            + "<body style=\"font-family: Arial, sans-serif;\">"
            + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
            + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
            + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
            + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
            + "<h3 style=\"color: #333;\">Verification Code:</h3>"
            + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
            + "</div>"
            + "</div>"
            + "</body>"
            + "</html>";
    
    
        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



    public String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 1000000;
        return String.valueOf(code);
    }
}