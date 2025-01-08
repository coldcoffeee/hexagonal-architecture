package com.vw.ecommerce.OrderService.application.out;

import java.util.Optional;
import java.util.UUID;

import com.vw.ecommerce.OrderService.domain.Product;

public interface ProductRepository {
    Optional<Product> getProductById(UUID productId);
    void updateStock(UUID productId, int quantity);
}
