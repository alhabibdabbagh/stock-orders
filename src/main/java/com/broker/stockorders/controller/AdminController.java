package com.broker.stockorders.controller;

import com.broker.stockorders.dto.response.OrderResponse;
import com.broker.stockorders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders/{orderId}/match")
    public ResponseEntity<OrderResponse> matchOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.matchOrder(orderId));
    }
}