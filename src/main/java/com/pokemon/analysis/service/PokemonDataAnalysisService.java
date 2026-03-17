package com.pokemon.analysis.service;

import com.pokemon.analysis.model.PokemonDataAnalysis;
import com.pokemon.analysis.repository.PokemonDataAnalysisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;


@Component
public class PokemonDataAnalysisService {

    // Fields
    private PokemonDataAnalysisRepository pokemonDataAnalysisRepository;

    // Constructor
    @Autowired
    public PokemonDataAnalysisService(PokemonDataAnalysisRepository pokemonDataAnalysisRepository) {
        this.pokemonDataAnalysisRepository = pokemonDataAnalysisRepository;
    }


    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemons() {
        return pokemonDataAnalysisRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByName(String name) {
        return pokemonDataAnalysisRepository.getByName(name)
            .map(Collections::singletonList)
            .orElse(Collections.emptyList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByPrice(float price) {
        return pokemonDataAnalysisRepository.getByPrice(price);
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByStock(String stock) {
        return pokemonDataAnalysisRepository.getByStock(stock);
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonBySku(int sku) {
        return pokemonDataAnalysisRepository.getBySku(sku);
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByCategory(String category) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getCategory().toLowerCase().contains(category.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByTag(String tag) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getTag().toLowerCase().contains(tag.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getByWeight(String weight) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getWeight() == weight)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByDimension(String dimension) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getDimension().equalsIgnoreCase(dimension))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByNameAndPrice(String name, float price) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name) && pokemon.getPrice() == price)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByNameAndStock(String name, String stock) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(pokemon.getName()) && pokemon.getStock().equalsIgnoreCase(stock))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByNameAndSku(String name, int sku) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(pokemon.getName()) && pokemon.getSku() == sku)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByNameAndCategory(String name, String category) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name) && pokemon.getCategory().toLowerCase().contains(category.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByNameAndTag(String name, String tag) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().equalsIgnoreCase(name.strip()) && pokemon.getTag().contains(tag.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByNameAndWeight(String name, String weight) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().strip().equalsIgnoreCase(name.strip()) && pokemon.getWeight().equalsIgnoreCase(weight))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByNameAndDimension(String name, String dimension) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getName().strip().equalsIgnoreCase(name.strip()) && pokemon.getDimension().strip().equalsIgnoreCase(dimension.strip()))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByPriceAndStock(float price, String stock) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getStock().equalsIgnoreCase(stock))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByPriceAndSku(float price, int sku) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getSku() == sku)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByPriceAndCategory(float price, String category) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getCategory().toLowerCase().contains(category.strip().toLowerCase()))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByPriceAndTag(float price, String tag) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getTag().toLowerCase().contains(tag.toLowerCase()))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByPriceAndWeight(float price, String weight) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getWeight().equalsIgnoreCase(weight.strip()))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PokemonDataAnalysis> getPokemonByPriceAndDimension(float price, String dimension) {
        return pokemonDataAnalysisRepository.findAll().stream()
            .filter(pokemon -> pokemon.getPrice() == price && pokemon.getDimension().equalsIgnoreCase(dimension.strip()))
            .collect(Collectors.toList());
    }


    @Transactional
    public PokemonDataAnalysis addPokemon(PokemonDataAnalysis pokemon) {
        pokemonDataAnalysisRepository.save(pokemon);
        return pokemon;
    }


    @Transactional
    public PokemonDataAnalysis updatePokemon(PokemonDataAnalysis updatePokemon) {
        PokemonDataAnalysis pokemon = pokemonDataAnalysisRepository
            .getByName(updatePokemon.getName())
            .orElseThrow(() -> new RuntimeException("Pokemon not found"));

        pokemon.setName(updatePokemon.getName());
        pokemon.setPrice(updatePokemon.getPrice());
        pokemon.setDescription(updatePokemon.getDescription());
        pokemon.setStock(updatePokemon.getStock());
        pokemon.setSku(updatePokemon.getSku());
        pokemon.setCategory(updatePokemon.getCategory());
        pokemon.setTag(updatePokemon.getTag());
        pokemon.setWeight(updatePokemon.getWeight());
        pokemon.setDimension(updatePokemon.getDimension());
        pokemon.setScrapingMethod(updatePokemon.getScrapingMethod());

        return pokemon;
    }


    @Transactional
    public void deletePokemon(String name) {
        pokemonDataAnalysisRepository.deleteByName(name);
    }

    @Transactional
    public void deletePokemonBySku(int sku) {
        pokemonDataAnalysisRepository.deleteBySku(sku);
    }

    @Transactional 
    public void deletePokemonByNameAndSku(String name, int sku) {
        pokemonDataAnalysisRepository.deleteByNameAndSku(name, sku);
    }

    @Transactional
    public void deletePokemonByPriceAndSku(float price, int sku) {
        pokemonDataAnalysisRepository.deleteByPriceAndSku(price, sku);
    }
}
