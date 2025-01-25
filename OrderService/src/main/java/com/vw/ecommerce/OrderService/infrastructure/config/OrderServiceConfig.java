package com.vw.ecommerce.OrderService.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vw.ecommerce.OrderService.application.OrderService;
import com.vw.ecommerce.OrderService.application.out.OrderRepository;
import com.vw.ecommerce.OrderService.application.out.PaymentRepository;
import com.vw.ecommerce.OrderService.application.out.ProductRepository;

@Configuration
public class OrderServiceConfig {

    @Bean
    public OrderService orderService(ProductRepository productRepository, OrderRepository orderRepository, PaymentRepository paymentRepository) {
        return new OrderService(productRepository, orderRepository, paymentRepository);
    }
}
