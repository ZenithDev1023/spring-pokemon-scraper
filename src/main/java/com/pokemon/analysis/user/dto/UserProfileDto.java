package com.pokemon.analysis.user.dto;

public record UserProfileDto (
    String email, 
    String username
) {
    public String getEmail() { return email; }
    public String getUsername() { return username; }
   
}
