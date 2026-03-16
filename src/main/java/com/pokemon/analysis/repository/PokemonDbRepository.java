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

    Optional<PokemonDb> findByPokemonName(String pokemonName);
    Optional<PokemonDb> findByPokedex(int pokedex);
    Optional<PokemonDb> findByPokemonNameAndPokedex(String pokemonName, int pokedex);

    List<PokemonDb> findByType1(String type1);
    List<PokemonDb> findByType2(String type2);
    List<PokemonDb> findByTotalStats(int totalStats);
    List<PokemonDb> findByHp(int hp);
    List<PokemonDb> findByAttack(int attack);
    List<PokemonDb> findByDefense(int defense);
    List<PokemonDb> findBySpAttack(int spAttack);
    List<PokemonDb> findBySpDefense(int spDefense);
    List<PokemonDb> findBySpeed(int speed);

    List<PokemonDb> findByPokemonNameAndType1(String pokemonName, String type1);
    List<PokemonDb> findByPokemonNameAndType2(String pokemonName, String type2);
    List<PokemonDb> findByPokemonNameAndTotalStats(String pokemonName, int totalStats);
    List<PokemonDb> findByPokemonNameAndHp(String pokemonName, int hp);
    List<PokemonDb> findByPokemonNameAndAttack(String pokemonName, int attack);
    List<PokemonDb> findByPokemonNameAndDefense(String pokemonName, int defense);
    List<PokemonDb> findByPokemonNameAndSpAttack(String pokemonName, int spAttack);
    List<PokemonDb> findByPokemonNameAndSpDefense(String pokemonName, int spDefense);
    List<PokemonDb> findByPokemonNameAndSpeed(String pokemonName, int speed);

    List<PokemonDb> findByPokedexAndType1(int pokedex, String type1);
    List<PokemonDb> findByPokedexAndType2(int pokedex, String type2);
    List<PokemonDb> findByPokedexAndTotalStats(int pokedex, int totalStats);
    List<PokemonDb> findByPokedexAndHp(int pokedex, int hp);
    List<PokemonDb> findByPokedexAndAttack(int pokedex, int attack);
    List<PokemonDb> findByPokedexAndDefense(int pokedex, int defense);
    List<PokemonDb> findByPokedexAndSpAttack(int pokedex, int spAttack);
    List<PokemonDb> findByPokedexAndSpDefense(int pokedex, int spDefense);
    List<PokemonDb> findByPokedexAndSpeed(int pokedex, int speed);
}
