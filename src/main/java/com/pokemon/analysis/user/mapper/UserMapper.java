package com.pokemon.analysis.user.mapper;

import org.springframework.stereotype.Component;

import com.pokemon.analysis.user.dto.UserProfileDto;
import com.pokemon.analysis.user.entity.User;

@Component
public class UserMapper {
    public UserProfileDto toUserProfileDto(final User user) {
        return new UserProfileDto(user.getEmail(), user.getUsername());
    }   
}
