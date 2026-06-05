package com.pokemon.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pokemon.analysis.user.dto.request.LoginRequestDTO;
import com.pokemon.analysis.user.dto.request.SignupRequestDTO;
import com.pokemon.analysis.user.dto.request.VerifyUserDTO;
import com.pokemon.analysis.user.model.entity.User;

import com.pokemon.analysis.user.service.AuthService;
import com.pokemon.analysis.user.service.JwtService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {
    
    private AuthService authService;
    private JwtService jwtService;


    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }


    @GetMapping("/verify")
    public String showVerifyPage(@RequestParam(required = false) String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("verifyUser", new VerifyUserDTO());

        return "verify";
    }


    @PostMapping("/signup")
    public String signup(
        @Valid SignupRequestDTO signupRequestDTO,
        BindingResult result,
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "redirect:/auth/signup";
        }

        try {
            authService.signup(signupRequestDTO);
            redirectAttributes.addAttribute("email", signupRequestDTO.getEmail());
            return "redirect:/auth/verify";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/signup";
        }
    }




    @PostMapping("/verify")
    public String verifyUser(
        @Valid VerifyUserDTO verifyUser,
        BindingResult result,
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "verify";
        }

        try {
            authService.verifyUser(verifyUser);
            redirectAttributes.addFlashAttribute("message", "Account verified successfully");
            return "redirect:/auth/login";

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/login";
        }
    }



    @PostMapping("/resend")
    public String resendVerificationCode(
        @RequestParam String email,
        RedirectAttributes redirectAttributes
    ) {
        try {
            authService.resendVerificationCode(email);
            redirectAttributes.addFlashAttribute("message", "Verification code sent to " + email);

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/auth/verify?email=" + email;
    }
}
