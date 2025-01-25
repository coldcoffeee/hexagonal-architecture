package com.vw.ecommerce.OrderService.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final UUID id;
    private final String name;
    private final String address;
    private final String email;
    private final String password;
}
