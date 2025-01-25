package com.vw.ecommerce.OrderService.adapter.out.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.vw.ecommerce.OrderService.adapter.out.jpa.entities.OrderEntity;
import com.vw.ecommerce.OrderService.application.out.OrderRepository;
import com.vw.ecommerce.OrderService.domain.Order;
import com.vw.ecommerce.OrderService.domain.OrderStatus;

// @Repository
public class JpaOrderRepository implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public JpaOrderRepository(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public UUID saveOrder(Order order) {
        OrderEntity orderEntity = OrderEntity.fromDomain(order);
        orderJpaRepository.save(orderEntity);
        return orderEntity.getId();
    }

    @Override
    public Optional<Order> findOrderById(UUID orderId) {
        return orderJpaRepository.findById(orderId).map(OrderEntity::toDomain);
    }

    @Override
    public List<Order> findOrdersByUserId(UUID userId) {
        return orderJpaRepository.findByUserId(userId).stream().map(OrderEntity::toDomain).toList();
    }

    @Override
    public void updateOrderStatus(UUID orderId, OrderStatus status) {
        var orderEntity = orderJpaRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orderEntity.setStatus(status);
        orderJpaRepository.save(orderEntity);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderJpaRepository.deleteById(orderId);
    }
}

interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByUserId(UUID userId);
}