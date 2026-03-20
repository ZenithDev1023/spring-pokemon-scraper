package com.pokemon.analysis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pokemon.analysis.model.PokemonDb;
import com.pokemon.analysis.service.PokemonDbService;

import org.springframework.ui.Model;



@Controller
public class MainController {

    private PokemonDbService pokemonService;

    @Autowired
    public MainController(PokemonDbService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        List<PokemonDb> pokemondb = pokemonService.getAllPokemon();
        
        Map<String, List<PokemonDb>> pokemonByType = new HashMap<>();
        
        for (PokemonDb pokemon : pokemondb) {
            String type1 = pokemon.getType1();
            String type2 = pokemon.getType2();

            pokemonByType.computeIfAbsent(type1, k -> new ArrayList<>()).add(pokemon);
            
            if (type2 != null && !type2.trim().isEmpty()) {
                pokemonByType.computeIfAbsent(type2, k -> new ArrayList<>()).add(pokemon);
            }
        }

        Map<String, List<PokemonDb>> sortedPokemonByType = new TreeMap<>(pokemonByType);

        model.addAttribute("pokemonByType", sortedPokemonByType);
        return "main";
    }
    
    

    @GetMapping("/type/{typeName}")
    public String showPokemonByType(@PathVariable String typeName, Model model) {
        List<PokemonDb> pokemondb = pokemonService.getAllPokemon();

        List<PokemonDb> typePokemon = new ArrayList<>();

        for (PokemonDb pokemon : pokemondb) {
            boolean type1Match = pokemon.getType1().equalsIgnoreCase(typeName);
            boolean type2Match = pokemon.getType2() != null && pokemon.getType2().equalsIgnoreCase(typeName);

            if (type1Match || type2Match) {
                typePokemon.add(pokemon);
            }
        }

        model.addAttribute("typeName", typeName);
        model.addAttribute("pokemonList", typePokemon);
        model.addAttribute("pokemonCount", typePokemon.size());

        return "type-details";
    }
}
