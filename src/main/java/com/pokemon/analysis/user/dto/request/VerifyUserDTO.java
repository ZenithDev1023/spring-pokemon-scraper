package com.pokemon.analysis.user.dto.request;

import lombok.Data;

@Data
public class VerifyUserDTO {

    private String email;

    private String verificationCode;
    
}
