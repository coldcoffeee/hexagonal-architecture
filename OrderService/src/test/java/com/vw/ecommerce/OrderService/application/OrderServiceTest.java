package com.vw.ecommerce.OrderService.application;
import com.vw.ecommerce.OrderService.application.out.OrderRepository;
import com.vw.ecommerce.OrderService.application.out.PaymentRepository;
import com.vw.ecommerce.OrderService.application.out.ProductRepository;
import com.vw.ecommerce.OrderService.domain.Order;
import com.vw.ecommerce.OrderService.domain.OrderItem;
import com.vw.ecommerce.OrderService.domain.OrderStatus;
import com.vw.ecommerce.OrderService.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrder_Success() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        OrderItem orderItem1 = new OrderItem(productId1, 2);
        OrderItem orderItem2 = new OrderItem(productId2, 1);
        Order order = new Order();
        order.setOrderItems(Arrays.asList(orderItem1, orderItem2));

        Product product1 = new Product(productId1.toString(), "Product 1", "Description 1", 100, 10);
        Product product2 = new Product(productId2.toString(), "Product 2", "Description 2", 200, 12);
        when(productRepository.getProductById(productId1)).thenReturn(Optional.of(product1));
        when(productRepository.getProductById(productId2)).thenReturn(Optional.of(product2));
        when(orderRepository.saveOrder(order)).thenReturn(orderId);

        orderService.placeOrder(order);

        assertEquals(400.0, order.getOrderTotal());
        assertEquals(OrderStatus.PENDING, order.getStatus());
        verify(paymentRepository).processPayment(orderId, 400.0);
    }

    @Test
    public void testPlaceOrder_InvalidProduct() {
        UUID productId = UUID.randomUUID();
        OrderItem orderItem = new OrderItem(productId, 2);
        Order order = new Order();
        order.setOrderItems(Arrays.asList(orderItem));

        when(productRepository.getProductById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> orderService.placeOrder(order));
    }

    @Test
    public void testPlaceOrder_InsufficientStock() {
        UUID productId = UUID.randomUUID();
        OrderItem orderItem = new OrderItem(productId, 5);
        Order order = new Order();
        order.setOrderItems(Arrays.asList(orderItem));

        Product product = new Product(productId.toString(), "Product 1", "Description", 100, 2);
        when(productRepository.getProductById(productId)).thenReturn(Optional.of(product));

        assertThrows(RuntimeException.class, () -> orderService.placeOrder(order));
    }

    @Test
    public void testGetOrderStatus_Success() {
        UUID orderId = UUID.randomUUID();
        Order order = new Order();
        order.setStatus(OrderStatus.PLACED);

        when(orderRepository.findOrderById(orderId)).thenReturn(Optional.of(order));

        OrderStatus status = orderService.getOrderStatus(orderId);

        assertEquals(OrderStatus.PLACED, status);
    }

    @Test
    public void testGetOrderStatus_InvalidOrder() {
        UUID orderId = UUID.randomUUID();

        when(orderRepository.findOrderById(orderId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> orderService.getOrderStatus(orderId));
    }

    @Test
    public void testUpdateOrderStatus() {
        UUID orderId = UUID.randomUUID();
        OrderStatus status = OrderStatus.CANCELLED;

        orderService.updateOrderStatus(orderId, status);

        ArgumentCaptor<OrderStatus> statusCaptor = ArgumentCaptor.forClass(OrderStatus.class);
        verify(orderRepository).updateOrderStatus(eq(orderId), statusCaptor.capture());
        assertEquals(OrderStatus.CANCELLED, statusCaptor.getValue());
    }
}
