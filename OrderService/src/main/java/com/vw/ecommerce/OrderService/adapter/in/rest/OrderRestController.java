package com.vw.ecommerce.OrderService.adapter.in.rest;

import org.springframework.web.bind.annotation.RestController;

import com.vw.ecommerce.OrderService.adapter.in.rest.dto.CreateOrderDTO;
import com.vw.ecommerce.OrderService.application.in.CreateAndGetOrderUsecase;
import com.vw.ecommerce.OrderService.domain.OrderStatus;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderRestController {
    private final CreateAndGetOrderUsecase orderUsecase;

    @PostMapping
    public UUID placeOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        var order = createOrderDTO.toDomain();
        orderUsecase.placeOrder(order);
        return order.getId();
    }

    @GetMapping("/{orderId}/status")
    public ResponseEntity<String> getOrderStatus(@PathVariable UUID orderId) {
        OrderStatus status = orderUsecase.getOrderStatus(orderId);
        return ResponseEntity.ok(status.toString());
    }
}
