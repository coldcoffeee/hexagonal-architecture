package com.vw.ecommerce.OrderService.application.out;

import com.vw.ecommerce.OrderService.domain.Order;
import com.vw.ecommerce.OrderService.domain.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    UUID saveOrder(Order order);

    Optional<Order> findOrderById(UUID orderId);

    List<Order> findOrdersByUserId(UUID userId);

    void updateOrderStatus(UUID orderId, OrderStatus status);

    void deleteOrder(UUID orderId);
}