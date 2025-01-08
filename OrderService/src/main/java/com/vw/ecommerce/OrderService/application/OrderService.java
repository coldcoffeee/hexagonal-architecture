package com.vw.ecommerce.OrderService.application;

import com.vw.ecommerce.OrderService.application.in.OrderUsecase;
import com.vw.ecommerce.OrderService.application.out.OrderRepository;
import com.vw.ecommerce.OrderService.application.out.PaymentRepository;
import com.vw.ecommerce.OrderService.application.out.ProductRepository;
// import com.vw.ecommerce.OrderService.application.UserRepository;
import com.vw.ecommerce.OrderService.domain.Order;
import com.vw.ecommerce.OrderService.domain.OrderItem;
import com.vw.ecommerce.OrderService.domain.OrderStatus;
import com.vw.ecommerce.OrderService.domain.Product;
// import com.vw.ecommerce.OrderService.domain.User;

import java.util.UUID;

public class OrderService implements OrderUsecase {

    // private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository,PaymentRepository paymentRepository) {
        // Canb e used in future to get user's address for integration with shipping service
        // this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void placeOrder(Order order) {
        double orderTotal = 0;
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.getProductById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("InvalidProduct"));
            if(product.getStock() < item.getQuantity()) {
                throw new RuntimeException("InsufficientStock");
            }
            orderTotal += product.getPrice() * item.getQuantity();
            productRepository.updateStock(item.getProductId(), product.getStock() - item.getQuantity());
        }
        order.setOrderTotal(orderTotal);
        order.setStatus(OrderStatus.PENDING);

        var orderId = orderRepository.saveOrder(order);
        paymentRepository.processPayment(orderId, orderTotal);
    }

    @Override
    public OrderStatus getOrderStatus(UUID orderId) {
        return orderRepository.findOrderById(orderId).orElseThrow(() -> new RuntimeException("InvalidOrder")).getStatus();
    }

    @Override
    public void updateOrderStatus(UUID orderId, OrderStatus status) {
        orderRepository.updateOrderStatus(orderId, status);
    }
}
