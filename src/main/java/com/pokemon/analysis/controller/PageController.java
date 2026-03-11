package com.pokemon.analysis.controller;

import com.pokemon.analysis.model.PokemonDataAnalysis;
import com.pokemon.analysis.service.PokemonDataAnalysisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "pokemons";
    }

    @GetMapping("/pokemon")
    public String pokemonPage(Model model) {
        List<PokemonDataAnalysis> pokemons = pokemonDataAnalysisService.getPokemons();
        model.addAttribute("pokemons", pokemons);
        return "pokemons";
    }


}
