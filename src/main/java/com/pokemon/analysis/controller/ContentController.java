package com.pokemon.analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.pokemon.analysis.user.service.AuthService;
import com.pokemon.analysis.user.dto.request.SignupRequestDTO;


@Controller
public class ContentController {

    @Autowired
    private AuthService authService;
    

    @GetMapping("/login")
    public String showLoginPage(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "registered", required = false) String registered, 
        Model model
    ) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }

        if (registered != null) {
            model.addAttribute("registered", "Successfully registered");
        }

        return "login";
    }


    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("signupRequest", new SignupRequestDTO());
        return "signup";
    }


}
