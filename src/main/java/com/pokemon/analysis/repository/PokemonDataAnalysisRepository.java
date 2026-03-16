package com.pokemon.analysis.repository;

import com.pokemon.analysis.model.PokemonDataAnalysis;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokemonDataAnalysisRepository extends JpaRepository<PokemonDataAnalysis, Long> {

    void deleteByName(String name);
    void deleteBySku(int sku);
    void deleteByNameAndSku(String name, int sku);
    void deleteByPriceAndSku(float price, int sku);

    // Unique
    Optional<PokemonDataAnalysis> getByName(String name);
    Optional<PokemonDataAnalysis> getByNameAndSku(String name, int sku);
    Optional<PokemonDataAnalysis> getByPriceAndSku(float price, int sku);

    List<PokemonDataAnalysis> getByPrice(float price);
    List<PokemonDataAnalysis> getByStock(String stock);
    List<PokemonDataAnalysis> getBySku(int sku);
    List<PokemonDataAnalysis> getByCategory(String category);
    List<PokemonDataAnalysis> getByTag(String tag);
    List<PokemonDataAnalysis> getByWeight(String weight);
    List<PokemonDataAnalysis> getByDimension(String dimension);

    List<PokemonDataAnalysis> getByNameAndPrice(String name, float price);
    List<PokemonDataAnalysis> getByNameAndStock(String name, String stock);
    List<PokemonDataAnalysis> getByNameAndCategory(String name, String category);
    List<PokemonDataAnalysis> getByNameAndTag(String name, String tag);
    List<PokemonDataAnalysis> getByNameAndWeight(String name, String weight);
    List<PokemonDataAnalysis> getByNameAndDimension(String name, String dimension);

    List<PokemonDataAnalysis> getByPriceAndStock(float price, String stock);
    List<PokemonDataAnalysis> getByPriceAndCategory(float price, String category); 
    List<PokemonDataAnalysis> getByPriceAndTag(float price, String tag);
    List<PokemonDataAnalysis> getByPriceAndWeight(float price, String weight);
    List<PokemonDataAnalysis> getByPriceAndDimension(float price, String dimension);
}
