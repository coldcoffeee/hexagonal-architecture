package com.vw.ecommerce.OrderService.application.in;

import com.vw.ecommerce.OrderService.domain.Order;
import com.vw.ecommerce.OrderService.domain.OrderStatus;

import java.util.UUID;

public interface CreateAndGetOrderUsecase {
    void placeOrder(Order order);
    OrderStatus getOrderStatus(UUID orderId);
}
