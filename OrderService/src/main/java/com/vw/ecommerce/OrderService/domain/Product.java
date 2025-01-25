package com.vw.ecommerce.OrderService.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final Integer price;
    private Integer stock;
}
