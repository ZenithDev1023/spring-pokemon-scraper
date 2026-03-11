package com.pokemon.analysis.service;

import com.pokemon.analysis.model.PokemonDb;
import com.pokemon.analysis.repository.PokemonDataAnalysisRepository;
import com.pokemon.analysis.repository.PokemonDbRepository;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class PokemonDbService {
    private PokemonDbRepository pokemonRepository;

    @Autowired
    public PokemonDbService(PokemonDbRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<PokemonDb> getPokemonByName(String pokemon_name) {
        return pokemonRepository.findByName(pokemon_name)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());

    }

    public List<PokemonDb> getPokemonByPokedexNumber(int pokedex_number) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByType1(String type1) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getType1().equalsIgnoreCase(type1.strip()))
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByType2(String type2) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getType2().equalsIgnoreCase(type2.strip()))
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByTotalStats(int total_stats) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getTotalStats() == total_stats)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByHp(int hp) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getHp() == hp)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByAttack(int attack) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getAttack() == attack)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByDefense(int defense) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getDefense() == defense)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonBySpAttack(int sp_attack) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getSpAttack() == sp_attack)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonBySpDefense(int sp_defense) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getSpDefense() == sp_defense)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonBySpeed(int speed) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getSpeed() == speed)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndType1(int pokedex_number, String type1) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getType1().equalsIgnoreCase(type1.strip()))
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndType2(int pokedex_number, String type2) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getType2().equalsIgnoreCase(type2.strip()))
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndTotalStats(int pokedex_number, int total_stats) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getTotalStats() == total_stats)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndHp(int pokedex_number, int hp) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getHp() == hp)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndAttack(int pokedex_number, int attack) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getAttack() == attack)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndDefense(int pokedex_number, int defense) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getDefense() == defense)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndSpAttack(int pokedex_number, int sp_attack) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getSpAttack() == sp_attack)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndSpDefense(int pokedex_number, int sp_defense) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getSpDefense() == sp_defense)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexNumberAndSpeed(int pokedex_number, int speed) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedexNumber() == pokedex_number && pokemon.getSpeed() == speed)
            .collect(Collectors.toList());
    }


    public PokemonDb addPokemon(PokemonDb Pokemon) {
        pokemonRepository.save(Pokemon);
        return Pokemon;
    }


    public PokemonDb updatePokemon(PokemonDb updatedPokemon) {
        Optional<PokemonDb> existingPokemon = pokemonRepository.findByName(updatedPokemon.getPokemonName());

        if (existingPokemon.isPresent()) {
            PokemonDb pokemonToUpdate = existingPokemon.get();
            pokemonToUpdate.setPokedexNumber(updatedPokemon.getPokedexNumber());
            pokemonToUpdate.setType1(updatedPokemon.getType1());
            pokemonToUpdate.setType2(updatedPokemon.getType2());
            pokemonToUpdate.setTotalStats(updatedPokemon.getTotalStats());
            pokemonToUpdate.setHp(updatedPokemon.getHp());
            pokemonToUpdate.setAttack(updatedPokemon.getAttack());
            pokemonToUpdate.setDefense(updatedPokemon.getDefense());
            pokemonToUpdate.setSpAttack(updatedPokemon.getSpAttack());
            pokemonToUpdate.setSpDefense(updatedPokemon.getSpDefense());
            pokemonToUpdate.setSpeed(updatedPokemon.getSpeed());

            pokemonRepository.save(updatedPokemon);
            return updatedPokemon;
        }
        return null;
    }


    @Transactional
    public void deleteByName(String pokemon_name) {
        pokemonRepository.deleteByName(pokemon_name);
    }

    @Transactional
    public void deleteByPokedexNumber(int pokedex_number) {
        pokemonRepository.deleteByPokedexNumber(pokedex_number);
    }
}
