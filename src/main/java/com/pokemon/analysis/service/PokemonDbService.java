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


    public List<PokemonDb> getPokemons() {
        return pokemonRepository.findAll();
    }

    public List<PokemonDb> getPokemonByName(String pokemonName) {
        return pokemonRepository.findByPokemonName(pokemonName)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());

    }

    public List<PokemonDb> getPokemonByPokedex(int pokedex) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex)
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

    public List<PokemonDb> getPokemonByTotalStats(int totalStats) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getTotalStats() == totalStats)
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

    public List<PokemonDb> getPokemonBySpAttack(int spAttack) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getSpAttack() == spAttack)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonBySpDefense(int spDefense) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getSpDefense() == spDefense)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonBySpeed(int speed) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getSpeed() == speed)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndType1(int pokedex, String type1) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getType1().equalsIgnoreCase(type1.strip()))
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndType2(int pokedex, String type2) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getType2().equalsIgnoreCase(type2.strip()))
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndTotalStats(int pokedex, int totalStats) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getTotalStats() == totalStats)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndHp(int pokedex, int hp) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getHp() == hp)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndAttack(int pokedex, int attack) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getAttack() == attack)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndDefense(int pokedex, int defense) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getDefense() == defense)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndSpAttack(int pokedex, int spAttack) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getSpAttack() == spAttack)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndSp_defense(int pokedex, int spDefense) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getSpDefense() == spDefense)
            .collect(Collectors.toList());
    }

    public List<PokemonDb> getPokemonByPokedexAndSpeed(int pokedex, int speed) {
        return pokemonRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPokedex() == pokedex && pokemon.getSpeed() == speed)
            .collect(Collectors.toList());
    }


    public PokemonDb addPokemon(PokemonDb Pokemon) {
        pokemonRepository.save(Pokemon);
        return Pokemon;
    }


    public PokemonDb updatePokemon(PokemonDb updatedPokemon) {
        Optional<PokemonDb> existingPokemon = pokemonRepository.findByPokemonName(updatedPokemon.getPokemonName());

        if (existingPokemon.isPresent()) {
            PokemonDb pokemonToUpdate = existingPokemon.get();
            pokemonToUpdate.setPokedex(updatedPokemon.getPokedex());
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
    public void deleteByName(String pokemonName) {
        pokemonRepository.deleteByPokemonName(pokemonName);
    }

    @Transactional
    public void deleteByPokedex(int pokedex) {
        pokemonRepository.deleteByPokedex(pokedex);
    }

    @Transactional
    public void deleteByNameAndPokedex(String pokemonName, int pokedex) {
        pokemonRepository.deleteByPokemonNameAndPokedex(pokemonName, pokedex);
    }
}
