package com.example.pokemon_data_analysis.Pokemon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pokemontable")
public class PokemonDataAnalysis {

    // Fields
    @Id
    @Column
    private String name;

    private float price;

    private String description;

    private int stock;

    private int sku;

    private String category;

    private String tag;

    private String weight;

    private String dimension;

    private String scraping_method;


    // Default Constructor
    public PokemonDataAnalysis() {}


    // Argument Constructor
    public PokemonDataAnalysis(String name, float price, String description, String stock, int sku, String category, String tag, String weight, String dimension, String scraping_method) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.sku = sku;
        this.category = category;
        this.tag = tag;
        this.weight = weight;
        this.dimension = dimension;
        this.scraping_method = scraping_method;
    }


    // Getters
    public String getName() { return name; }
    public float getPrice() { return price; }
    public String getDescription() { return description; }
    public int getStock() { return stock; }
    public int getSku() { return sku; }
    public String getCategory() { return category; }
    public String getTag() { return tag; }
    public String getWeight() { return weight; }
    public String getDimension() { return dimension; }
    public String getScrapingMethod() { return scraping_method; }


    public String getString() {
        return String.format(
            "Name: %s, Price: %.4f, Description: %s, Stock: %d, Sku: %d, Category: %s, Tag: %s, Weight: %.2f, Dimension: %s, Scraping-Method: %s",
            name, price, description, stock, sku, category, tag, weight, dimension, scraping_method
        );
    }
}
