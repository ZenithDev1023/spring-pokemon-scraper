package com.example.pokemon_data_analysis.Pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping
public class PokemonDataAnalysisController {
    
    // Fields
    private PokemonDataAnalysisService pokemonDataAnalysisService;

    @Autowired
    public PokemonDataAnalysisController(PokemonDataAnalysisService pokemonDataAnalysisService) {
        this.pokemonDataAnalysisService = pokemonDataAnalysisService;
    }
}
