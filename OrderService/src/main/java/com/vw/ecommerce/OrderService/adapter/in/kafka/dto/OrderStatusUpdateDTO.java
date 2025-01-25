package com.vw.ecommerce.OrderService.adapter.in.kafka.dto;

import com.vw.ecommerce.OrderService.domain.OrderStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderStatusUpdateDTO {
    @NotBlank
    private final String orderId;
    @NotBlank
    private final OrderStatus status;
}
