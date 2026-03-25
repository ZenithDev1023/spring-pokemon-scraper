package com.pokemon.analysis.user;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.analysis.service.Auth.UserService;
import com.pokemon.analysis.user.dto.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getUserProfile(
        final Authentication authentication) {
            final var user = 
                userService.getUserByUsername(authentication.getName());
            
            return ResponseEntity.ok(userMapper, toUserProfileDto(user));
        }
}
