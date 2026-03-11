package com.pokemon.analysis.repository;

import com.pokemon.analysis.model.PokemonDataAnalysis;
import com.pokemon.analysis.service.PokemonDataAnalysisService;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokemonDataAnalysisRepository extends JpaRepository<PokemonDataAnalysis, Long> {
    void deleteByName(String name);
    void deleteBySku(int sku);

    Optional<PokemonDataAnalysis> getByName(String name);

    List<PokemonDataAnalysis> getByPrice(float price);
    List<PokemonDataAnalysis> getByStock(int stock);
    List<PokemonDataAnalysis> getBySku(int sku);
    List<PokemonDataAnalysis> getByCategory(String category);
    List<PokemonDataAnalysis> getByTag(String tag);
    List<PokemonDataAnalysis> getByWeight(String weight);
    List<PokemonDataAnalysis> getByDimension(String dimension);

    List<PokemonDataAnalysis> getByNameAndPrice(String name, float price);
    List<PokemonDataAnalysis> getByNameAndStock(String name, String weight);
    List<PokemonDataAnalysis> getByNameAndSku(String name, int sku);
    List<PokemonDataAnalysis> getByNameAndCategory(String name, String category);
    List<PokemonDataAnalysis> getByNameAndTag(String name, String tag);
    List<PokemonDataAnalysis> getByNameAndWeight(String name, String weight);
    List<PokemonDataAnalysis> getByNameAndDimension(String name, String dimension);

    List<PokemonDataAnalysis> getByPriceAndStock(float price, int stock);
    List<PokemonDataAnalysis> getByPriceAndSku(float price, int sku);
    List<PokemonDataAnalysis> getByPriceAndCategory(float price, String category); 
    List<PokemonDataAnalysis> getByPriceAndTag(float price, String tag);
    List<PokemonDataAnalysis> getByPriceAndWeight(float price, String weight);
    List<PokemonDataAnalysis> getByPriceAndDimension(float price, String dimension);
}
