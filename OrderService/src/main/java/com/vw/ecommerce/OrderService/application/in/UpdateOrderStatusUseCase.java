package com.vw.ecommerce.OrderService.application.in;

import java.util.UUID;

import com.vw.ecommerce.OrderService.domain.OrderStatus;

public interface UpdateOrderStatusUseCase {
    void updateOrderStatus(UUID orderId, OrderStatus orderStatus);

}
