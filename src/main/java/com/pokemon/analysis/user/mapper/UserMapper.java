package com.pokemon.analysis.user;

import org.springframework.stereotype.Component;

import com.pokemon.analysis.user.dto.UserProfileDto;

@Component
public class UserMapper {
    public UserProfileDto toUserProfileDto(final User user) {
        return new UserProfileDto(user.getEmail(), user.getUsername());
    }   
}
