package com.pokemon.analysis.user.dto;

public record RegistrationRequestDto (
    String username,
    String email,
    String password
) {
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}