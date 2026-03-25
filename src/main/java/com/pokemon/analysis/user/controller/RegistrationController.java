package com.pokemon.analysis.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.analysis.service.Auth.UserRegistrationService;
import com.pokemon.analysis.user.dto.RegistrationRequestDto;
import com.pokemon.analysis.user.dto.RegistrationResponseDto;

import jakarta.validation.Valid;




import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final UserRegistrationMapper userRegistrationMapper;


    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser (
      @Valid @RequestBody final RegistrationRequestDto registrationDTO) 
      {
        final var registeredUser = userRegistrationService
            .registerUser(userRegistrationMapper.toEntity(registrationDTO));
            
        return ResponseEntity.ok(
            userRegistrationMapper.toRegistrationResponseDto(registeredUser)
        );
    }
}
