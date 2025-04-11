package com.broker.stockorders.controller;

import com.broker.stockorders.dto.response.AssetResponse;
import com.broker.stockorders.dto.response.OrderResponse;
import com.broker.stockorders.service.AssetService;
import com.broker.stockorders.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
//@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AssetService assetService;

    public OrderController(OrderService orderService, AssetService assetService) {
        this.orderService = orderService;
        this.assetService = assetService;
    }

    @GetMapping("/{customerId}/orders")
    public List<OrderResponse> getOrdersByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @PageableDefault(size = 20, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (startDate != null && endDate != null) {
            return orderService.getOrdersByCustomerAndDateRange(customerId, startDate, endDate, pageable);
        }
        return orderService.getOrdersByCustomerId(customerId, pageable);
    }

    @DeleteMapping("/{customerId}/orders/{orderId}")
    public void deleteOrder(@PathVariable Long customerId, @PathVariable Long orderId) {
        orderService.deleteOrder(customerId, orderId);
    }

    @GetMapping("/{customerId}/assets")
    public List<AssetResponse> getAssetsByCustomerId(
            @PathVariable Long customerId
    ) {
        return assetService.getAssetsByCustomerId(customerId);
    }

}