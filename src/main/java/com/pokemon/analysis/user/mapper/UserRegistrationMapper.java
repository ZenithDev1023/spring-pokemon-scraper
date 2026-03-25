package com.pokemon.analysis.user;

import org.springframework.stereotype.Component;

import com.pokemon.analysis.user.dto.RegistrationRequestDto;
import com.pokemon.analysis.user.dto.RegistrationResponseDto;

@Component
public class UserRegistrationMapper {
    public User toEntity(RegistrationRequestDto registrationRequestDto) {
        final var user = new User();
        user.setUsername(registrationRequestDto.username());
        user.setEmail(registrationRequestDto.email());
        user.setPassword(registrationRequestDto.password());
        return user;
    }

    public RegistrationResponseDto toRegistrationResponseDto(final User user) {
        return new RegistrationResponseDto(user.getUsername(), user.getEmail());
    }

    
}
