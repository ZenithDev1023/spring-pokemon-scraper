package com.pokemon.analysis.service.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.analysis.repository.UserRepository;
import com.pokemon.analysis.user.User;

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


    @Transactional
    public void deleteByUsernameAndPassword(String username, String password) {
        userRepository.deleteByUsernameAndPassword(username, password);
    }   

    @Transactional
    public void deleteByEmailAndPassword(String email, String password) {
        userRepository.deleteByEmailAndPassword(email, password);
    }
}
