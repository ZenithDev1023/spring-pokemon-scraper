package com.pokemon.analysis.scrapeme;

import com.pokemon.analysis.service.scrapeme.PokemonDataAnalysisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;

import java.util.List;


@RequestMapping
public class PokemonDataAnalysisController {
    
    // Fields
    private PokemonDataAnalysisService pokemonDataAnalysisService;

    @Autowired
    public PokemonDataAnalysisController(PokemonDataAnalysisService pokemonDataAnalysisService) {
        this.pokemonDataAnalysisService = pokemonDataAnalysisService;
    }


    @GetMapping
    public List<PokemonDataAnalysis> getPokemons(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) float price,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) String stock,
        @RequestParam(required = false) int sku,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String tag,
        @RequestParam(required = false) String weight,
        @RequestParam(required = false) String dimension,
        @RequestParam(required = false) String scraping_method) {

            if (name != null && price >= 0.0) {
                return pokemonDataAnalysisService.getPokemonByNameAndPrice(name, price);
            }
            else if (name != null && stock != null) {
                return pokemonDataAnalysisService.getPokemonByNameAndStock(name, stock);
            }
            else if (name != null && sku >= 0) {
                return pokemonDataAnalysisService.getPokemonByNameAndSku(name, sku);
            }
            else if (name != null && category != null) {
                return pokemonDataAnalysisService.getPokemonByNameAndCategory(name, category);
            }
            else if (name != null && tag != null) {
                return pokemonDataAnalysisService.getPokemonByNameAndTag(name, tag);
            }
            else if (name != null && weight != null) {
                return pokemonDataAnalysisService.getPokemonByNameAndWeight(name, weight);
            }
            else if (name != null && dimension != null) {
                return pokemonDataAnalysisService.getPokemonByNameAndDimension(name, dimension);
            }
            else if (price >= 0 && stock != null) {
                return pokemonDataAnalysisService.getPokemonByPriceAndStock(price, stock);
            }
            else if (price >= 0 && sku >= 0) {
                return pokemonDataAnalysisService.getPokemonByPriceAndSku(price, sku);
            }
            else if (price >= 0 && category != null) {
                return pokemonDataAnalysisService.getPokemonByPriceAndCategory(price, category);
            }
            else if (price >= 0 && tag != null) {
                return pokemonDataAnalysisService.getPokemonByPriceAndTag(price, tag);
            }
            else if (price >= 0 && weight != null) {
                return pokemonDataAnalysisService.getPokemonByPriceAndWeight(price, weight);
            }
            else if (price >= 0 && dimension != null) {
                return pokemonDataAnalysisService.getPokemonByPriceAndDimension(price, dimension);
            }
            else {
                return pokemonDataAnalysisService.getPokemons();
            }
    }


    @PostMapping
    public ResponseEntity<PokemonDataAnalysis> addPokemon(@RequestBody PokemonDataAnalysis pokemon) {
        PokemonDataAnalysis createPokemon = pokemonDataAnalysisService.addPokemon(pokemon);
        return new ResponseEntity<>(createPokemon, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<PokemonDataAnalysis> updatePokemon(@RequestBody PokemonDataAnalysis pokemon) {
        PokemonDataAnalysis updatePokemon = pokemonDataAnalysisService.updatePokemon(pokemon);
        if (updatePokemon != null) {
            return new ResponseEntity<>(updatePokemon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(updatePokemon, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{name}")
    public ResponseEntity<String> deletePokemon(@PathVariable String name) {
        pokemonDataAnalysisService.deletePokemon(name);
        return new ResponseEntity<>("Pokemon deleted successfully!", HttpStatus.OK);
    }

}
