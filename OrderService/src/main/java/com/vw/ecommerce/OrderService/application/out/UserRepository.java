package com.vw.ecommerce.OrderService.application.out;

import java.util.UUID;

import com.vw.ecommerce.OrderService.domain.User;

public interface UserRepository {
    User getUserById(UUID userId);
}
