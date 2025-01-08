package com.vw.ecommerce.OrderService.application.out;

import java.util.UUID;

public interface PaymentRepository {
    boolean processPayment(UUID orderId, double amount);
}
