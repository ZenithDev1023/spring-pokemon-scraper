package com.pokemon.analysis.pokemondb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pokemondb")
public class PokemonDb {
    // Fields
    @Id
    @Column(name = "pokemon_name")
    private String pokemonName;

    @Column(name = "pokedex_number")
    private int pokedex;

    private String type1;
    private String type2;

    @Column(name = "total_stats")
    private int totalStats;
    
    private int hp;
    private int attack;
    private int defense;

    @Column(name = "sp_attack")
    private int spAttack;

    @Column(name = "sp_defense")
    private int spDefense;
    private int speed;


    // Default Constructor
    public PokemonDb() {}


    // Argument Constructor
    public PokemonDb(String pokemonName, int pokedex, String type1, String type2, int totalStats, int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
        this.pokemonName = pokemonName;
        this.pokedex = pokedex;
        this.type1 = type1;
        this.type2 = type2;
        this.totalStats = totalStats;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
    }


    // Getters
    public String getPokemonName() { return pokemonName; }
    public int getPokedex() { return pokedex; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public int getTotalStats() { return totalStats; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpAttack() { return spAttack; }
    public int getSpDefense() { return spDefense; }
    public int getSpeed() { return speed; }


    // Setters
    public void setPokemonName(String pokemonName) { this.pokemonName = pokemonName; }
    public void setPokedex(int pokedex) { this.pokedex = pokedex; }
    public void setType1(String type1) { this.type1 = type1; }
    public void setType2(String type2) { this.type2 = type2; }
    public void setTotalStats(int totalStats) { this.totalStats = totalStats; }
    public void setHp(int hp) { this.hp = hp; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setSpAttack(int spAttack) { this.spAttack = spAttack; }
    public void setSpDefense(int spDefense) { this.spDefense = spDefense; }
    public void setSpeed(int speed) { this.speed = speed; }


    public String toString() {
        return String.format(
            "Pokemon-Name: %s, Pokedex-Number: %d, Type1: %s, Type2: %s, Total-Stats: %d, Hp: %d, Attack: %d, Defense: %d, Sp-Attack: %d, Sp-Defense: %d, Speed: %d",
            pokemonName, pokedex, type1, type2, totalStats, hp, attack, defense, spAttack, spDefense, speed
        );
    }
}
