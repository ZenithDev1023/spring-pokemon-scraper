package com.pokemon.analysis.service.Auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.pokemon.analysis.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override 
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(user -> User.builder()
                .username(username.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build()
            ).orElseThrow(() -> new UsernameNotFoundException(
                "User with username [%s] not found.".formatted(username)));
    }
}
