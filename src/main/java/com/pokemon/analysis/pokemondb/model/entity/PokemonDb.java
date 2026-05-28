package com.pokemon.analysis.pokemondb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="pokemondb")
public class PokemonDb {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pokemon name is required")
    @Size(min = 1, max = 50, message = "Pokemon name must be between 1 and 50 characters")
    @Column(name = "pokemon_name", nullable = false, unique = true)
    private String pokemonName;
    
    @Min(value = 1, message = "Pokedex number must be atleast 1")
    @Max(value = 9999, message = "Pokedex number cannot exceed 9999")
    @NotNull(message = "Pokedex number is required")
    @Column(name = "pokedex_number", nullable = false, unique = true, updatable = false)
    private int pokedex;

    @NotBlank(message = "Type 1 is required")
    @Size(min = 1, max = 50, message = "Type 1 must be betweem 1 amd 50 characters")
    @Column(name = "type_1", nullable = false)
    private String type1;

    @Column(name = "type_2")
    @Size(min = 0, max = 50, message = "Type 2 must be between 0 and 50 characters")
    private String type2;

    @Min(value = 1, message = "Total stats must be at least 1")
    @Max(value = 9999, message = "Total stats cannot exceed 9999")
    @NotNull(message = "Total stats is required")
    @Column(name = "total_stats", nullable = false)
    private int totalStats;

    @Min(value = 1, message = "HP must be at least 1")
    @Max(value = 255, message = "HP cannot exceed 255")
    @NotNull(message = "Hp is required")
    @Column(nullable = false)
    private int hp;

    @Min(value = 1, message = "Attack must be at least 1")
    @Max(value = 255, message = "Attack cannot exceed 255")
    @NotNull(message = "Attack stat is required")
    @Column(nullable = false)
    private int attack;

    @Min(value = 1, message = "Defense must be at least 1")
    @Max(value = 255, message = "Defense cannot exceed 255")
    @NotNull(message = "Defense stat is required")
    @Column(nullable = false)
    private int defense;

    @Min(value = 1, message = "Sp attack must be at least 1")
    @Max(value = 255, message = "Sp attack cannot exceed 255")
    @NotNull(message = "Sp attack is required")
    @Column(name = "sp_attack", nullable = false)
    private int spAttack;

    @Min(value = 1, message = "Sp defense must be at least 1")
    @Max(value = 255, message = "Sp defense cannot exceed 255")
    @NotNull(message = "Sp defense is required")
    @Column(name = "sp_defense", nullable = false)
    private int spDefense;

    @Min(value = 1, message = "Speed must be at least 1")
    @Max(value = 255, message = "Speed cannot exceed 255")
    @NotNull(message = "Speed is required")
    @Column(nullable = false)
    private int speed;


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


    @Override
    public String toString() {
        return String.format(
            "Pokemon-Name: %s, Pokedex-Number: %d, Type1: %s, Type2: %s, Total-Stats: %d, Hp: %d, Attack: %d, Defense: %d, Sp-Attack: %d, Sp-Defense: %d, Speed: %d",
            pokemonName, pokedex, type1, type2, totalStats, hp, attack, defense, spAttack, spDefense, speed
        );
    }
}
