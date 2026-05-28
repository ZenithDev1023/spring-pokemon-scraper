package com.pokemon.analysis.scrapeme.entity;

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
@Table(name="scrapeme")
public class PokemonDataAnalysis {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 50, message = "Pokemon name must be between 1 and 50 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @Min(value = 1, message = "Price must be atleast £1")
    @Max(value = 9999, message = "Price cannot exceed £9999")
    @NotNull(message = "Price is required")
    @Column(nullable = false)
    private float price;

    @NotBlank(message = "Description is required")
    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "Stock is required")
    @Size(min = 1, max = 9999, message = "Stock must be between 1 and 9999")
    @Column(nullable = false)
    private String stock;

    @Min(value = 1, message = "Sku must be atleast 1")
    @Max(value = 9999, message = "Sku cannot exceed 9999")
    @NotNull(message = "Sku is required")
    @Column(nullable = false, unique = true)
    private int sku;

    @NotBlank(message = "Category is required")
    @Size(min = 1, max = 255, message = "Category must be between 1 and 255 characters")
    @Column(nullable = false)
    private String category;

    @NotBlank(message = "Tag is required")
    @Size(min = 1, max = 255, message = "Tag must be between 1 and 255 characters")
    @Column(nullable = false)
    private String tag;

    @NotBlank(message = "Weight is required")
    @Size(min = 1, max = 50, message = "Weight must be between 1 and 50 characters")
    @Column(nullable = false)
    private String weight;

    @NotBlank(message = "Dimensions is required")
    @Size(min = 1, max = 50, message = "Dimensions must be between 1 and 50 characters")
    @Column(nullable = false)
    private String dimension;

    @NotBlank(message = "Scraping method is required")
    @Size(min = 1, max = 50, message = "Scraping method must be between 1 and 50 characters")
    @Column(name = "scraping_method", nullable = false)
    private String scrapingMethod;


    public PokemonDataAnalysis(String name, float price, String description, String stock, int sku, String category, String tag, String weight, String dimension, String scrapingMethod) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.sku = sku;
        this.category = category;
        this.tag = tag;
        this.weight = weight;
        this.dimension = dimension;
        this.scrapingMethod = scrapingMethod;
    }


    public String toString() {
        return String.format(
            "Name: %s, Price: %.4f, Description: %s, Stock: %s, Sku: %d, Category: %s, Tag: %s, Weight: %s, Dimension: %s, Scraping-Method: %s",
            name, price, description, stock, sku, category, tag, weight, dimension, scrapingMethod
        );
    }
}
