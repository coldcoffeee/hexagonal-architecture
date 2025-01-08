package com.vw.ecommerce.OrderService.domain;

public class Product {
    private final String id;
    private final String name;
    private final String description;
    private final Integer price;
    private final Integer stock;

    public Product(String id, String name, String description, Integer price, Integer stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }
    
}
