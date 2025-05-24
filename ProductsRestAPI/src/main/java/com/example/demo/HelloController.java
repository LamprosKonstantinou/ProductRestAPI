package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;



@RestController
public class HelloController {

    // 🔹 Η λίστα πρέπει να είναι ΜΕΣΑ στην κλάση
    private List<Product> productList = new ArrayList<>();

    @GetMapping("/hello")
    public Map<String, String> sayHello(@RequestParam(required = false, defaultValue = "World") String name) {
        String message = "Hello, " + name + "!";
        return Map.of("message", message);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productList;
    }



    @PostMapping("/products")
    public Map<String, String> createProduct(@RequestBody @Valid ProductDTO dto) {
        int newId = productList.size() + 1;

        Product newProduct = new Product(newId, dto.getName(), dto.getPrice());
        productList.add(newProduct);

        return Map.of("message", "Product " + dto.getName() + " added!");
    }



    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, String> deleteProductById(@PathVariable int id) {
        boolean removed = productList.removeIf(p -> p.getId() == id);

        if (removed) {
            return Map.of("message", "Product with id " + id + " deleted!");
        } else {
            return Map.of("message", "Product with id " + id + " not found.");
        }



    }

    @PutMapping("/products/{id}")
    public Map<String, String> updateProduct(@PathVariable int id, @RequestBody @Valid ProductDTO dto) {
        for (Product product : productList) {
            if (product.getId() == id) {
                product.setName(dto.getName());
                product.setPrice(dto.getPrice());
                return Map.of("message", "Product with id " + id + " updated!");
            }
        }
        return Map.of("message", "Product with id " + id + " not found.");
    }


}








