package com.pokemon.analysis.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.analysis.user.entity.User;
import com.pokemon.analysis.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(final String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.GONE, 
                "The user account has been deleted or inactivated"
            ));
    }


    public User registerUser(String username, String email, String password) throws Exception {
        if (userRepository.existsByUsername(username)) {
            throw new Exception("Username already exists!");
        }

        if (userRepository.existsByEmail(email)) {
            throw new Exception("Email already exists!");
        } 

        User user = new User(username, email, password);
        
        return userRepository.save(user);
    }


    @Transactional
    public void deleteByUsernameAndPassword(String username, String password) {
        userRepository.deleteByUsernameAndPassword(username, password);
    }   

    @Transactional
    public void deleteByEmailAndPassword(String email, String password) {
        userRepository.deleteByEmailAndPassword(email, password);
    }
}
