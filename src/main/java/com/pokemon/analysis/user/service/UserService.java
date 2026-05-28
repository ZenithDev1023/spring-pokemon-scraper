package com.pokemon.analysis.user.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemon.analysis.user.model.entity.User;
import com.pokemon.analysis.user.repository.UserRepository;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV', 'PM')")
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }


    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV', 'TESTER'")
    public ResponseEntity<Optional<User>> getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }


    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV', 'TESTER')")
    public ResponseEntity<User> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }    
}
