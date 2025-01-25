package com.vw.ecommerce.OrderService.adapter.in.rest.dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.vw.ecommerce.OrderService.domain.Order;
import com.vw.ecommerce.OrderService.domain.OrderItem;
import com.vw.ecommerce.OrderService.domain.OrderStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateOrderDTO {
    @NotBlank
    private final UUID userId;
    @NotEmpty
    private final Map<UUID, Integer> items;

    public Order toDomain() {
        List<OrderItem> orderItems = items.entrySet().stream()
                .map(entry -> new OrderItem(entry.getKey(), entry.getValue()))
                .toList();
        return new Order(UUID.randomUUID(),0.0, OrderStatus.PENDING, orderItems, userId);
    }
}