package com.pokemon.analysis.repository;

import com.pokemon.analysis.model.PokemonDb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PokemonDbRepository extends JpaRepository<PokemonDb, Long> {
    void deleteByName(String pokemon_name);
    void deleteByPokedexNumber(int pokedex_number);

    Optional<PokemonDb> findByName(String pokemon_name);
    Optional<PokemonDb> findByPokedexNumber(int pokedex_number);

    List<PokemonDb> findByType1(String type1);
    List<PokemonDb> findByType2(String type2);
    List<PokemonDb> findByTotalStats(int total_stats);
    List<PokemonDb> findByHp(int hp);
    List<PokemonDb> findByAttack(int attack);
    List<PokemonDb> findByDefense(int defense);
    List<PokemonDb> findBySpAttack(int sp_attack);
    List<PokemonDb> findBySpDefense(int sp_defense);
    List<PokemonDb> findBySpeed(int speed);

    List<PokemonDb> findByNameAndPokedexNumber(String pokemon_name, int pokedex_number);
    List<PokemonDb> findByNameAndType1(String pokemon_name, String type1);
    List<PokemonDb> findByNameAndType2(String pokemon_name, String type2);
    List<PokemonDb> findByNameAndTotalStats(String pokemon_name, int total_stats);
    List<PokemonDb> findByNameAndHp(String pokemon_name, int hp);
    List<PokemonDb> findByNameAndAttack(String pokemon_name, int attack);
    List<PokemonDb> findByNameAndDefense(String pokemon_name, int defense);
    List<PokemonDb> findByNameAndSpAttack(String pokemon_name, int sp_attack);
    List<PokemonDb> findByNameAndSpDefense(String pokemon_name, int sp_defense);
    List<PokemonDb> findByNameAndSpeed(String pokemon_name, int speed);

    List<PokemonDb> findByPokedexNumberAndType1(int pokedex_number, String type1);
    List<PokemonDb> findByPokedexNumberAndType2(int pokedex_number, String type2);
    List<PokemonDb> findByPokedexNumberAndTotalStats(int pokedex_number, int total_stats);
    List<PokemonDb> findByPokedexNumberAndHp(int pokedex_number, int hp);
    List<PokemonDb> findByPokedexNumberAndAttack(int pokedex_number, int attack);
    List<PokemonDb> findByPokedexNumberAndDefense(int pokedex_number, int defense);
    List<PokemonDb> findByPokedexNumberAndSpAttack(int pokedex_number, int sp_attack);
    List<PokemonDb> findByPokedexNumberAndSpDefense(int pokedex_number, int sp_defense);
    List<PokemonDb> findByPokedexNumberAndSpeed(int pokedex_number, int speed);
}
