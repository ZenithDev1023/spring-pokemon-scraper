package com.pokemon.analysis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pokemondb")
public class PokemonDb {
    // Fields
    @Column
    @Id
    private String pokemon_name;
    private int pokedex_number;
    private String type1;
    private String type2;
    private int total_stats;
    private int hp;
    private int attack;
    private int defense;
    private int sp_attack;
    private int sp_defense;
    private int speed;

    // Default Constructor
    public PokemonDb() {}


    // Argument Constructor
    public PokemonDb(String pokemon_name, int pokedex_number, String type1, String type2, int total_stats, int hp, int attack, int defense, int sp_attack, int sp_defense, int speed) {
        this.pokemon_name = pokemon_name;
        this.pokedex_number = pokedex_number;
        this.type1 = type1;
        this.type2 = type2;
        this.total_stats = total_stats;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.sp_attack = sp_attack;
        this.sp_defense = sp_defense;
        this.speed = speed;
    }


    // Getters
    public String getPokemonName() { return pokemon_name; }
    public int getPokedexNumber() { return pokedex_number; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public int getTotalStats() { return total_stats; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpAttack() { return sp_attack; }
    public int getSpDefense() { return sp_defense; }
    public int getSpeed() { return speed; }


    // Setters
    public void setPokemonName(String pokemon_name) { this.pokemon_name = pokemon_name; }
    public void setPokedexNumber(int pokedex_number) { this.pokedex_number = pokedex_number; }
    public void setType1(String type1) { this.type1 = type1; }
    public void setType2(String type2) { this.type2 = type2; }
    public void setTotalStats(int total_stats) { this.total_stats = total_stats; }
    public void setHp(int hp) { this.hp = hp; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setSpAttack(int sp_attack) { this.sp_attack = sp_attack; }
    public void setSpDefense(int sp_defense) { this.sp_defense = sp_defense; }
    public void setSpeed(int speed) { this.speed = speed; }


    public String toString() {
        return String.format(
            "Pokemon-Name: %s, Pokedex-Number: %d, Type1: %s, Type2: %s, Total-Stats: %d, Hp: %d, Attack: %d, Defense: %d, Sp-Attack: %d, Sp-Defense: %d, Speed: %d",
            pokemon_name, pokedex_number, type1, type2, total_stats, hp, attack, defense, sp_attack, sp_defense, speed
        );
    }
}
