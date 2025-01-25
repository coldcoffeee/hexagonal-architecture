package com.vw.ecommerce.OrderService.mocks;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.vw.ecommerce.OrderService.application.out.ProductRepository;
import com.vw.ecommerce.OrderService.domain.Product;

@Repository
public class ProductRepositoryMock implements ProductRepository {

    private static List<Product> products = List.of(
        new Product(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), "Pen", "One", 100, 10),
        new Product(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"), "Notebook", "Two", 200, 20),
        new Product(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"), "Crayon", "Three", 300, 30)
    );

    @Override
    public Optional<Product> getProductById(UUID productId) {
        System.out.println("ProductRepositoryMock.getProductById");
        return products.stream().filter(p -> p.getId().equals(productId)).findFirst();
    }

    @Override
    public void updateStock(UUID productId, int quantity) {
        System.out.println("ProductRepositoryMock.updateStock");
        products.stream().filter(p -> p.getId().equals(productId)).forEach(p -> p.setStock(p.getStock() - quantity));
    }

}
