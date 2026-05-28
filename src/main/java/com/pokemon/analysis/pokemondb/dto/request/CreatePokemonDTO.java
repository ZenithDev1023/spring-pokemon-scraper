package com.pokemon.analysis.pokemondb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreatePokemonDTO {

    @NotBlank(message = "Pokemon name is required")
    @Size(min = 1, max = 50, message = "Pokemon name must be between 1 and 50 characters")
    private String pokemonName;

    @NotNull(message = "Pokedex is required")
    private int pokedex;

    @NotBlank(message = "Type 1 is required")
    private String type1;

    private String type2;

    @NotNull(message = "Total stats is required")
    private int totalStats;

    @NotNull(message = "Hp is required")
    private int hp;

    @NotNull(message = "Attack is required")
    private int attack;

    @NotNull(message = "Defense is required")
    private int defense;

    @NotNull(message = "Sp-attack is required")
    private int spAttack;

    @NotNull(message = "Sp-defense is required")
    private int spDefense;

    @NotNull(message = "Speed is required")
    private int speed;
    
}
