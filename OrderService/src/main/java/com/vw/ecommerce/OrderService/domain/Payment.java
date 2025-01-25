package com.vw.ecommerce.OrderService.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Payment {
    private UUID orderId;
    private double amount;  
}
