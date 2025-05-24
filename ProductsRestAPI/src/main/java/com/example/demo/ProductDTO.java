package com.example.demo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    public ProductDTO() {}

    public ProductDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
}

