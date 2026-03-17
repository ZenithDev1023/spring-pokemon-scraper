package com.pokemon.analysis.repository;

import com.pokemon.analysis.model.PokemonDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PokemonDbRepository extends JpaRepository<PokemonDb, String> {

    void deleteByPokemonName(String pokemonName);
    void deleteByPokedex(int pokedex);
    void deleteByPokemonNameAndPokedex(String pokemonName, int pokedex);

    Optional<PokemonDb> findByPokemonNameAndPokedex(String pokemonName, int pokedex);

    Optional<PokemonDb> findByPokemonName(String pokemonName);
    List<PokemonDb> findPokemonByPokedex(int pokedex);
    List<PokemonDb> findPokemonByType1(String type1);
    List<PokemonDb> findPokemonByType2(String type2);
    List<PokemonDb> findPokemonByTotalStats(int totalStats);
    List<PokemonDb> findPokemonByHp(int hp);
    List<PokemonDb> findPokemonByAttack(int attack);
    List<PokemonDb> findPokemonByDefense(int defense);
    List<PokemonDb> findPokemonBySpAttack(int spAttack);
    List<PokemonDb> findPokemonBySpDefense(int spDefense);
    List<PokemonDb> findPokemonBySpeed(int speed);

    List<PokemonDb> findByPokemonNameAndType1(String pokemonName, String type1);
    List<PokemonDb> findByPokemonNameAndType2(String pokemonName, String type2);
    List<PokemonDb> findByPokemonNameAndTotalStats(String pokemonName, int totalStats);
    List<PokemonDb> findByPokemonNameAndHp(String pokemonName, int hp);
    List<PokemonDb> findByPokemonNameAndAttack(String pokemonName, int attack);
    List<PokemonDb> findByPokemonNameAndDefense(String pokemonName, int defense);
    List<PokemonDb> findByPokemonNameAndSpAttack(String pokemonName, int spAttack);
    List<PokemonDb> findByPokemonNameAndSpDefense(String pokemonName, int spDefense);
    List<PokemonDb> findByPokemonNameAndSpeed(String pokemonName, int speed);

    List<PokemonDb> findPokemonByPokedexAndType1(int pokedex, String type1);
    List<PokemonDb> findPokemonByPokedexAndType2(int pokedex, String type2);
    List<PokemonDb> findPokemonByPokedexAndTotalStats(int pokedex, int totalStats);
    List<PokemonDb> findPokemonByPokedexAndHp(int pokedex, int hp);
    List<PokemonDb> findPokemonByPokedexAndAttack(int pokedex, int attack);
    List<PokemonDb> findPokemonByPokedexAndDefense(int pokedex, int defense);
    List<PokemonDb> findPokemonByPokedexAndSpAttack(int pokedex, int spAttack);
    List<PokemonDb> findPokemonByPokedexAndSpDefense(int pokedex, int spDefense);
    List<PokemonDb> findPokemonByPokedexAndSpeed(int pokedex, int speed);
}
