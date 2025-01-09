package com.vw.ecommerce.OrderService.adapter.out.kafka;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vw.ecommerce.OrderService.adapter.out.kafka.dto.PaymentDTO;
import com.vw.ecommerce.OrderService.application.out.PaymentRepository;

import lombok.AllArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class KafkaPaymentRepository implements PaymentRepository {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "payment_topic";

    @Override
    public boolean processPayment(UUID orderId, double amount) {
        PaymentDTO paymentDTO = new PaymentDTO(orderId, amount);
        try {
            String message = objectMapper.writeValueAsString(paymentDTO);
            kafkaTemplate.send(TOPIC, message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
