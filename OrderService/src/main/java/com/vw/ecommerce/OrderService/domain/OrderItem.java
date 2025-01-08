package com.vw.ecommerce.OrderService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class OrderItem {
    private final UUID productId;
    private final Integer quantity;
}
