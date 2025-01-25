package com.vw.ecommerce.OrderService.mocks;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.vw.ecommerce.OrderService.application.out.UserRepository;
import com.vw.ecommerce.OrderService.domain.User;

@Repository
public class UserRepositoryMock implements UserRepository {

    private static List<User> users = List.of(
        new User(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), "John Doe", "USA", "password123", "1234567890"),
        new User(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"), "Jane Smith", "UK", "password456", "9876543210")
    );

    @Override
    public User getUserById(UUID userId) {
        System.out.println("UserRepositoryMock.getUserById");
        return users.stream().filter(u -> u.getId().equals(userId)).findFirst().orElse(null);
    }
    
}
