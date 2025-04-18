package com.broker.stockorders.controller;

import com.broker.stockorders.dto.request.OrderRequest;
import com.broker.stockorders.dto.response.AssetResponse;
import com.broker.stockorders.dto.response.OrderResponse;
import com.broker.stockorders.service.AssetService;
import com.broker.stockorders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private AssetService assetService;

    @InjectMocks
    private OrderController orderController;

    private final Long customerId = 1L;
    private final Long orderId = 1L;
    private final LocalDateTime now = LocalDateTime.now();
    private final LocalDateTime startDate = now.minusDays(1);
    private final LocalDateTime endDate = now.plusDays(1);
    private final Pageable pageable = PageRequest.of(0, 20, Sort.by("createDate").descending());
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetails = User.withUsername("testuser")
                .password("password")
                .authorities("ROLE_USER")
                .build();
    }

    @Test
    void getOrdersByCustomerId_shouldReturnOrdersWithoutDateFilter() {
        // Arrange
        OrderResponse orderResponse = new OrderResponse(/* parameters */);
        List<OrderResponse> expected = Collections.singletonList(orderResponse);

        when(orderService.getOrdersByCustomerId(eq(customerId), any(Pageable.class)))
                .thenReturn(expected);

        // Act
        ResponseEntity<List<OrderResponse>> response =
                orderController.getOrdersByCustomerId(customerId, null, null, pageable);

        // Assert
        assertEquals(expected, response.getBody());
        verify(orderService).getOrdersByCustomerId(customerId, pageable);
        verify(orderService, never()).getOrdersByCustomerAndDateRange(any(), any(), any(), any());
    }

    @Test
    void getOrdersByCustomerId_shouldReturnOrdersWithDateFilter() {
        // Arrange
        OrderResponse orderResponse = new OrderResponse(/* parameters */);
        List<OrderResponse> expected = Collections.singletonList(orderResponse);

        when(orderService.getOrdersByCustomerAndDateRange(eq(customerId), eq(startDate), eq(endDate), any(Pageable.class)))
                .thenReturn(expected);

        // Act
        ResponseEntity<List<OrderResponse>> response =
                orderController.getOrdersByCustomerId(customerId, startDate, endDate, pageable);

        // Assert
        assertEquals(expected, response.getBody());
        verify(orderService).getOrdersByCustomerAndDateRange(customerId, startDate, endDate, pageable);
        verify(orderService, never()).getOrdersByCustomerId(any(), any());
    }

    @Test
    void deleteOrder_shouldCallServiceAndReturnNoContent() {
        // Arrange
        doNothing().when(orderService).deleteOrder(customerId, orderId);

        // Act
        ResponseEntity<Void> response = orderController.deleteOrder(customerId, orderId);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(orderService).deleteOrder(customerId, orderId);
    }

    @Test
    void getAssetsByCustomerId_shouldReturnAssets() {
        // Arrange
        AssetResponse assetResponse = new AssetResponse(/* parameters */);
        List<AssetResponse> expected = Collections.singletonList(assetResponse);

        when(assetService.getAssetsByCustomerId(customerId)).thenReturn(expected);

        // Act
        ResponseEntity<List<AssetResponse>> response =
                orderController.getAssetsByCustomerId(customerId);

        // Assert
        assertEquals(expected, response.getBody());
        verify(assetService).getAssetsByCustomerId(customerId);
    }

    @Test
    void createOrder_shouldReturnCreatedOrder() {
        // Arrange
        OrderRequest request = new OrderRequest(/* parameters */);
        OrderResponse expected = new OrderResponse(/* parameters */);

        when(orderService.createOrder(eq(request), eq(userDetails.getUsername())))
                .thenReturn(expected);

        // Act
        ResponseEntity<OrderResponse> response =
                orderController.createOrder(request, userDetails);

        // Assert
        assertEquals(expected, response.getBody());
        verify(orderService).createOrder(request, userDetails.getUsername());
    }

    @Test
    void createOrder_shouldHandleNullUserDetails() {
        // Arrange
        OrderRequest request = new OrderRequest(/* parameters */);
        OrderResponse expected = new OrderResponse(/* parameters */);

        when(orderService.createOrder(eq(request), isNull()))
                .thenReturn(expected);

        // Act
        ResponseEntity<OrderResponse> response =
                orderController.createOrder(request, null);

        // Assert
        assertEquals(expected, response.getBody());
        verify(orderService).createOrder(request, null);
    }
}