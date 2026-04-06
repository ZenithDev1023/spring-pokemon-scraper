package com.pokemon.analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pokemon.analysis.user.service.UserService;
import com.pokemon.analysis.user.entity.User;


@Controller
public class ContentController {

    private UserService userService;

    @Autowired
    public ContentController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
            );
            return "redirect:/login?registered=true";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }


    @GetMapping("/login")
    public String login(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "registered", required = false) String registered,
        Model model
    ) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or passsword");
        }

        if (registered != null) {
            model.addAttribute("message", "Registration successful! Please login.");
        }

        return "login";
    }
}
