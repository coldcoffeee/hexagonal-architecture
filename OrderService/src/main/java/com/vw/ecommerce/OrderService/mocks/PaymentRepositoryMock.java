package com.vw.ecommerce.OrderService.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.vw.ecommerce.OrderService.application.out.PaymentRepository;
import com.vw.ecommerce.OrderService.domain.Payment;

@Repository
@Primary
public class PaymentRepositoryMock implements PaymentRepository {

    private static List<Payment> payments = new ArrayList<>();

    @Override
    public boolean processPayment(UUID orderId, double amount) {
        System.out.println("PaymentRepositoryMock.processPayment");
        Payment payment = new Payment(orderId, amount);
        payments.add(payment);
        return true;
    }
}
