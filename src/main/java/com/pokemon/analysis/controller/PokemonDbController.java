package com.pokemon.analysis.controller;

import com.pokemon.analysis.model.PokemonDb;
import com.pokemon.analysis.service.PokemonDbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/pokemon")
public class PokemonDbController {
    // Fields
    private PokemonDbService pokemonService;

    @Autowired
    public PokemonDbController(PokemonDbService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GetMapping
    public List<PokemonDb> getPokemon(
        @RequestParam(required = false) int pokedex,
        @RequestParam(required = false) String type1,
        @RequestParam(required = false) String type2,
        @RequestParam(required = false) int totalStats,
        @RequestParam(required = false) int hp,
        @RequestParam(required = false) int attack,
        @RequestParam(required = false) int defense,
        @RequestParam(required = false) int spAttack,
        @RequestParam(required = false) int spDefense,
        @RequestParam(required = false) int speed
    ) {
        if (pokedex >= 0) {
            return pokemonService.getPokemonByPokedex(pokedex);
        } else if (type1 != null) {
            return pokemonService.getPokemonByType1(type1);
        } else if (type2 != null) {
            return pokemonService.getPokemonByType2(type2);
        } else if (totalStats >= 0) {
            return pokemonService.getPokemonByTotalStats(totalStats);
        } else if (hp >= 0) {
            return pokemonService.getPokemonByHp(hp);
        } else if (attack >= 0) {
            return pokemonService.getPokemonByAttack(attack);
        } else if (defense >= 0) {
            return pokemonService.getPokemonByDefense(defense);
        } else if (spAttack >= 0) {
            return pokemonService.getPokemonBySpAttack(spAttack);
        } else if (spDefense >= 0) {
            return pokemonService.getPokemonBySpDefense(spDefense);
        } else {
            return pokemonService.getPokemonBySpeed(speed);
        } 
    }


    @PostMapping
    public ResponseEntity<PokemonDb> addPokemon(@RequestBody PokemonDb Pokemon) {
        PokemonDb pokemon = pokemonService.addPokemon(Pokemon);
        if (pokemon != null) {
            return new ResponseEntity<>(pokemon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(pokemon, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping
    public ResponseEntity<PokemonDb> updatePokemon(@RequestBody PokemonDb Pokemon) {
        PokemonDb updatedPokemon = pokemonService.updatePokemon(Pokemon);
        if (updatedPokemon != null) {
            return new ResponseEntity<>(updatedPokemon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(updatedPokemon, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{pokemon_name}")
    public ResponseEntity<String> deletePokemon(@PathVariable String pokemonName) {
        pokemonService.deleteByName(pokemonName);
        return new ResponseEntity<>("Pokemon deleted successfully", HttpStatus.OK);
    }
}
