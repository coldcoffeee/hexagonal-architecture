package com.vw.ecommerce.OrderService.adapter.out.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.vw.ecommerce.OrderService.domain.OrderItem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    private UUID productId;
    private Integer quantity;

    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItem toDomain() {
        return new OrderItem(productId, quantity);
    }

    public static OrderItemEntity fromDomain(OrderItem orderItem) {
        OrderItemEntity orderItemEntity = new OrderItemEntity(orderItem.getProductId(), orderItem.getQuantity());
        return orderItemEntity;
    }

}
