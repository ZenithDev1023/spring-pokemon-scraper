package com.pokemon.analysis.user.dto;

public record AuthenticationResponseDto (String token) {
    public String getToken() { return token; }
}
