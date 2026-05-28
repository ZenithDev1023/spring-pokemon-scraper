package com.pokemon.analysis.pokemondb.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PokemonResponse {

    private Long id;
    private String pokemonName;
    private int pokedex;
    
}
