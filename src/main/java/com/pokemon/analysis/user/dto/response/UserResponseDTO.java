package com.pokemon.analysis.user.dto.response;

import com.pokemon.analysis.user.model.enums.Role;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private String message;

}
