package com.vw.ecommerce.OrderService.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.vw.ecommerce.OrderService.application.out.OrderRepository;
import com.vw.ecommerce.OrderService.domain.Order;
import com.vw.ecommerce.OrderService.domain.OrderStatus;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryMock implements OrderRepository {

    private static List<Order> orders = new ArrayList<Order>();

    @Override
    public UUID saveOrder(Order order) {
        System.out.println("OrderRepositoryMock.saveOrder");
        order.setId(UUID.randomUUID());
        orders.add(order);
        return order.getId();
    }

    @Override
    public Optional<Order> findOrderById(UUID orderId) {
        System.out.println("OrderRepositoryMock.findOrderById");
        return orders.stream()
                     .filter(order -> order.getId().equals(orderId))
                     .findFirst();
    }

    @Override
    public List<Order> findOrdersByUserId(UUID userId) {
        System.out.println("OrderRepositoryMock.findOrdersByUserId");
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    @Override
    public void updateOrderStatus(UUID orderId, OrderStatus status) {
        System.out.println("OrderRepositoryMock.updateOrderStatus");
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                order.setStatus(status);
                break;
            }
        }
    }

    @Override
    public void deleteOrder(UUID orderId) {
        System.out.println("OrderRepositoryMock.deleteOrder");
        orders.removeIf(order -> order.getId().equals(orderId));
    }
    
}
