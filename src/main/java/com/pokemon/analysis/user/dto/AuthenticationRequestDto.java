package com.pokemon.analysis.user.dto;

public record AuthenticationRequestDto (
    String username, 
    String password
) {
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
