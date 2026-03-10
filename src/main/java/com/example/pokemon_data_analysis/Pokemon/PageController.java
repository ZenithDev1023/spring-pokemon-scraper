package com.example.pokemon_data_analysis.Pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import src.main.java.com.example.pokemon_data_analysis.Pokemon.PokemonDataAnalysis;
import src.main.java.com.example.pokemon_data_analysis.Pokemon.PokemonDataAnalysisService;

import java.util.List;


@Controller
public class PageController {

    // Fields
    private final PokemonDataAnalysisService pokemonDataAnalysisService;

    @Autowired
    public PageController(PokemonDataAnalysisService pokemonDataAnalysisService) {
        this.pokemonDataAnalysisService = pokemonDataAnalysisService;
    }


    @GetMapping("/")
    public String home() {

    }

    @GetMapping("/pokemon")
    public String pokemonPage(Model model) {
        
    }


}
