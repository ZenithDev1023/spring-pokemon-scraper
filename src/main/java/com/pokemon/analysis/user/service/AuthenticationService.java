package com.pokemon.analysis.service.Auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.pokemon.analysis.manager.AuthenticationManager;
import com.pokemon.analysis.user.dto.AuthenticationRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationRequestDto authenticate(final AuthenticationRequestDto request) {
        final var authToken = UsernamePasswordAuthenticationToken
            .unauthenticated(request.username(), request.password());

        final var authentication = authenticationManager
            .authenticate(authToken);

        final var token = jwtService.generateToken(request.username());
        return new AuthenticationResponseDto();
    }
    
}
