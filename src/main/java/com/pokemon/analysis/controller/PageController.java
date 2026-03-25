package com.pokemon.analysis.controller;

import com.pokemon.analysis.pokemondb.model.PokemonDb;
import com.pokemon.analysis.pokemondb.service.PokemonDbService;
import com.pokemon.analysis.scrapeme.model.PokemonDataAnalysis;
import com.pokemon.analysis.scrapeme.service.PokemonDataAnalysisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class PageController {

    // Fields
    private final PokemonDataAnalysisService pokemonDataAnalysisService;
    private final PokemonDbService pokemonDbService;

    @Autowired
    public PageController(
        PokemonDataAnalysisService pokemonDataAnalysisService, 
        PokemonDbService pokemonDbService
    ) {
        this.pokemonDataAnalysisService = pokemonDataAnalysisService;
        this.pokemonDbService = pokemonDbService;
    }
    

    @GetMapping("/pokemondb")
    public String pokemomDbPokemonPage(Model model) {
        List<PokemonDb> pokemons = pokemonDbService.getPokemons();
        model.addAttribute("pokemondb", pokemons);
        return "pokemondb";
    }

    @GetMapping("/scrapeme")
    public String scrapeMePokemonPage(Model model) {
        List<PokemonDataAnalysis> pokemons = pokemonDataAnalysisService.getPokemons();
        model.addAttribute("scrapeme", pokemons);
        return "scrapeme";
    }
}
