package com.pokemon.analysis.service.pokemondb;

import com.pokemon.analysis.pokemondb.PokemonDb;
import com.pokemon.analysis.repository.PokemonDbRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class PokemonDbService {
    private PokemonDbRepository pokemonRepository;

    @Autowired
    public PokemonDbService(PokemonDbRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }


    @Transactional
    public List<PokemonDb> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PokemonDb> getPokemonByNameAndPokedex(String pokemonName, int pokedex) {
        return pokemonRepository.findByPokemonNameAndPokedex(pokemonName, pokedex);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemons() {
        return pokemonRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PokemonDb> getPokemonByName(String pokemonName) {
        return pokemonRepository.findByPokemonName(pokemonName);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedex(int pokedex) {
        return pokemonRepository.findPokemonByPokedex(pokedex);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByType1(String type1) {
        return pokemonRepository.findPokemonByType1(type1);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByType2(String type2) {
        return pokemonRepository.findPokemonByType2(type2);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByTotalStats(int totalStats) {
        return pokemonRepository.findPokemonByTotalStats(totalStats);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByHp(int hp) {
        return pokemonRepository.findPokemonByHp(hp);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByAttack(int attack) {
        return pokemonRepository.findPokemonByAttack(attack);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByDefense(int defense) {
        return pokemonRepository.findPokemonByDefense(defense);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonBySpAttack(int spAttack) {
        return pokemonRepository.findPokemonBySpAttack(spAttack);
    }

    public List<PokemonDb> getPokemonBySpDefense(int spDefense) {
        return pokemonRepository.findPokemonBySpDefense(spDefense);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonBySpeed(int speed) {
        return pokemonRepository.findPokemonBySpeed(speed);
    }


    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndType1(int pokedex, String type1) {
        return pokemonRepository.findPokemonByPokedexAndType1(pokedex, type1);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndType2(int pokedex, String type2) {
        return pokemonRepository.findPokemonByPokedexAndType2(pokedex, type2);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndTotalStats(int pokedex, int totalStats) {
        return pokemonRepository.findPokemonByPokedexAndTotalStats(pokedex, totalStats);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndHp(int pokedex, int hp) {
        return pokemonRepository.findPokemonByPokedexAndHp(pokedex, hp);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndAttack(int pokedex, int attack) {
        return pokemonRepository.findPokemonByPokedexAndAttack(pokedex, attack);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndDefense(int pokedex, int defense) {
        return pokemonRepository.findPokemonByPokedexAndDefense(pokedex, defense);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndSpAttack(int pokedex, int spAttack) {
        return pokemonRepository.findPokemonByPokedexAndSpAttack(pokedex, spAttack);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndSpDefense(int pokedex, int spDefense) {
        return pokemonRepository.findPokemonByPokedexAndSpDefense(pokedex, spDefense);
    }

    @Transactional(readOnly = true)
    public List<PokemonDb> getPokemonByPokedexAndSpeed(int pokedex, int speed) {
        return pokemonRepository.findPokemonByPokedexAndSpeed(pokedex, speed);
    }


    @Transactional
    public PokemonDb addPokemon(PokemonDb Pokemon) {
        pokemonRepository.save(Pokemon);
        return Pokemon;
    }


    @Transactional
    public PokemonDb updatePokemon(PokemonDb updatedPokemon) {
        PokemonDb pokemon = pokemonRepository
            .findByPokemonName(updatedPokemon.getPokemonName())
            .orElseThrow(() -> new RuntimeException("Pokemon not found"));

        pokemon.setPokedex(updatedPokemon.getPokedex());
        pokemon.setType1(updatedPokemon.getType1());
        pokemon.setType2(updatedPokemon.getType2());
        pokemon.setTotalStats(updatedPokemon.getTotalStats());
        pokemon.setHp(updatedPokemon.getHp());
        pokemon.setAttack(updatedPokemon.getAttack());
        pokemon.setDefense(updatedPokemon.getDefense());
        pokemon.setSpAttack(updatedPokemon.getSpAttack());
        pokemon.setSpDefense(updatedPokemon.getSpDefense());
        pokemon.setSpeed(updatedPokemon.getSpeed());

        return pokemon;
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
