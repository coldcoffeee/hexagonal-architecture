package com.vw.ecommerce.OrderService.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private UUID id;
    private Double orderTotal;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private UUID userId;
}