package com.pokemon.analysis.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.analysis.service.Auth.AuthenticationService;
import com.pokemon.analysis.user.dto.AuthenticationRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationRequestDto> authenticate(
        @RequestBody final AuthenticationRequestDto authenticationRequestDto) {
            return ResponseEntity.ok(
                authenticationService.authenticate(authenticationRequestDto)
            );
        }
    
}
