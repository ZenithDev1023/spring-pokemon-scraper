package com.example.pokemon_data_analysis.Pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.main.java.com.example.pokemon_data_analysis.Pokemon.PokemonDataAnalysis;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Collections;


@Component
public class PokemonDataAnalysisService {
    // Fields
    private PokemonDataAnalysisRepository pokemonDataAnalysisRepository;

    @Autowired
    public PokemonDataAnalysisService(PokemonDataAnalysisRepository pokemonDataAnalysisRepository) {
        this.pokemonDataAnalysisRepository = pokemonDataAnalysisRepository;
    }


    public List<PokemonDataAnalysis> getPokemons() {
        return pokemonDataAnalysisRepository.findAll();
    }


    public List<PokemonDataAnalysis> getPokemonByName(String name) {
        return pokemonDataAnalysisRepository.getByName(name)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());
    }


    public List<PokemonDataAnalysis> getPokemonByPrice(float price) {
        return pokemonDataAnalysisRepository.getByPrice(price);
    }

    public List<PokemonDataAnalysis> getPokemonByStock(int stock) {
        return pokemonDataAnalysisRepository.getByStock(stock);
    }

    public List<PokemonDataAnalysis> getPokemonBySku(int sku) {
        return pokemonDataAnalysisRepository.getBySku(sku);
    }

    public List<PokemonDataAnalysis> getPokemonByCategory(String category) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getCategory().toLowerCase().contains(category.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByTag(String tag) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getTag().toLowerCase().contains(tag.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getByWeight(String weight) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getWeight() == weight)
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByDimension(String dimension) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getDimension().equalsIgnoreCase(dimension))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByNameAndPrice(String name, float price) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name) && pokemon.getPrice() == price)
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByNameAndStock(String name, int stock) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(pokemon.getName()) && pokemon.getStock() == stock)
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByNameAndSku(String name, int sku) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(pokemon.getName()) && pokemon.getSku() == sku)
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByNameAndCategory(String name, String category) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name) && pokemon.getCategory().toLowerCase().contains(category.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByNameAndTag(String name, String tag) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name.strip()) && pokemon.getTag().contains(tag.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByNameAbdWeight(String name, String weight) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().strip().equalsIgnoreCase(name.strip()) && pokemon.getWeight().equalsIgnoreCase(weight))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByNameAndDimension(String name, String dimension) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().strip().equalsIgnoreCase(name.strip()) && pokemon.getDimension().strip().equalsIgnoreCase(dimension.strip()))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByPriceAndStock(float price, int stock) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getStock() == stock)
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByPriceAndSku(float price, int sku) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getSku() == sku)
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByPriceAndCategory(float price, String category) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getCategory().toLowerCase().contains(category.strip().toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByPriceAndTag(float price, String tag) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getTag().toLowerCase().contains(tag.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByPriceAndWeight(float price, String weight) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getWeight().equalsIgnoreCase(weight.strip()))
            .collect(Collectors.toList());
    }

    public List<PokemonDataAnalysis> getPokemonByPriceAndDimension(float price, String dimension) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getDimension().equalsIgnoreCase(dimension.strip()))
            .collect(Collectors.toList());
    }



}
