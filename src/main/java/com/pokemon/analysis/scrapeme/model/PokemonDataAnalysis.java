package com.pokemon.analysis.scrapeme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="scrapeme")
public class PokemonDataAnalysis {

    // Fields
    @Column
    @Id
    private String name;
    private float price;
    private String description;
    private String stock;
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
    public String getStock() { return stock; }
    public int getSku() { return sku; }
    public String getCategory() { return category; }
    public String getTag() { return tag; }
    public String getWeight() { return weight; }
    public String getDimension() { return dimension; }
    public String getScrapingMethod() { return scraping_method; }


    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(float price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setStock(String stock) { this.stock = stock; }
    public void setSku(int sku) { this.sku = sku; }
    public void setCategory(String category) { this.category = category; }
    public void setTag(String tag) { this.tag = tag; }
    public void setWeight(String weight) { this.weight = weight; }
    public void setDimension(String dimension) { this.dimension = dimension; }
    public void setScrapingMethod(String scraping_method) { this.scraping_method = scraping_method; }


    public String getString() {
        return String.format(
            "Name: %s, Price: %.4f, Description: %s, Stock: %s, Sku: %d, Category: %s, Tag: %s, Weight: %.2f, Dimension: %s, Scraping-Method: %s",
            name, price, description, stock, sku, category, tag, weight, dimension, scraping_method
        );
    }
}
