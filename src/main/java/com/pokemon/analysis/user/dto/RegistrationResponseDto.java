package com.pokemon.analysis.user.dto;

public record RegistrationResponseDto(
    String username,
    String email
) {
    public String getUsername() { return username; }
    public String getEmail() { return email; }
}