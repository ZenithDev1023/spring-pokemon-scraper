package com.pokemon.analysis.service.Auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pokemon.analysis.repository.UserRepository;
import com.pokemon.analysis.user.User;
import com.pokemon.analysis.user.dto.RegistrationRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.pokemon.analysis.exception.ValidationException;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(RegistrationRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername()) || 
            userRepository.existsByEmail(request.getEmail())) {
            
            throw new ValidationException ("Username or Email already exists");
        }

            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            return userRepository.save(user);
    }
}
