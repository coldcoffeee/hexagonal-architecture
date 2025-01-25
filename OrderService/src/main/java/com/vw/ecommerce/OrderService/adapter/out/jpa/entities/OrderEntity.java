package com.vw.ecommerce.OrderService.adapter.out.jpa.entities;

import com.vw.ecommerce.OrderService.domain.Order;
import com.vw.ecommerce.OrderService.domain.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Double orderTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;

    @Column(nullable = false)
    private UUID userId;

    public Order toDomain() {
        return new Order(
            this.id,
            this.orderTotal,
            this.status,
            this.orderItems.stream().map(OrderItemEntity::toDomain).toList(),
            this.userId
        );
    }

    public static OrderEntity fromDomain(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setOrderTotal(order.getOrderTotal());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setOrderItems(order.getOrderItems().stream().map(OrderItemEntity::fromDomain).toList());
        orderEntity.setUserId(order.getUserId());
        orderEntity.getOrderItems().forEach(item -> item.setOrder(orderEntity));
        return orderEntity;
    }
}
