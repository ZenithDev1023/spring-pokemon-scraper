package com.pokemon.analysis.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pokemon.analysis.user.dto.RegistrationRequestDto;
import com.pokemon.analysis.user.entity.User;
import com.pokemon.analysis.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.pokemon.analysis.exception.ValidationException;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;

    @Transactional
    public User registerUser(RegistrationRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername()) || 
            userRepository.existsByEmail(request.getEmail())) {
            
            throw new ValidationException ("Username or Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }
}
