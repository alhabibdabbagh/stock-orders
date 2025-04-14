package com.broker.stockorders.controller;

import com.broker.stockorders.dto.request.OrderRequest;
import com.broker.stockorders.dto.response.AssetResponse;
import com.broker.stockorders.dto.response.OrderResponse;
import com.broker.stockorders.service.AssetService;
import com.broker.stockorders.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<Void>  deleteOrder(@PathVariable Long customerId, @PathVariable Long orderId) {
        orderService.deleteOrder(customerId, orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}/assets")
    public List<AssetResponse> getAssetsByCustomerId(
            @PathVariable Long customerId
    ) {
        return assetService.getAssetsByCustomerId(customerId);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody OrderRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(orderService.createOrder(request, userDetails.getUsername()));
    }

/*    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(orderService.getOrders(customerId, startDate, endDate));
    }

*/

}