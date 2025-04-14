package com.broker.stockorders.service;

import com.broker.stockorders.dto.model.OrderSide;
import com.broker.stockorders.dto.model.OrderStatus;
import com.broker.stockorders.dto.request.OrderRequest;
import com.broker.stockorders.dto.response.OrderResponse;
import com.broker.stockorders.entity.Asset;
import com.broker.stockorders.entity.Customer;
import com.broker.stockorders.entity.Order;
import com.broker.stockorders.repository.AssetRepository;
import com.broker.stockorders.repository.CustomerRepository;
import com.broker.stockorders.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Service
//@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AssetRepository assetRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, AssetRepository assetRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.assetRepository = assetRepository;
    }

    public List<OrderResponse> getOrdersByCustomerAndDateRange(
            Long customerId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    ) {
        return orderRepository.findByCustomerIdAndCreateDateBetween(customerId, startDate, endDate, pageable)
                .map(this::convertToOrderResponse).toList();
    }

    private OrderResponse convertToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderSide(order.getOrderSide() != null ? order.getOrderSide().name() : "UNKNOWN")
                .size(order.getSize())
                .price(order.getPrice())
                .status(order.getStatus() != null ? order.getStatus().name() : "UNKNOWN")
                .createDate(order.getCreateDate())
                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : null)
                .assetId(order.getAsset() != null ? order.getAsset().getId() : null)
                .build();
    }

    public List<OrderResponse> getOrdersByCustomerId(Long customerId, Pageable pageable) {
        // Customer kontrolü (opsiyonel)
        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("Customer not found");
        }

        return orderRepository.findByCustomerId(customerId, pageable)
                .map(this::convertToOrderResponse).toList();
    }

    @Transactional
    public OrderResponse matchOrder(Long orderId) {
        // 1. Order'ı bul ve validasyon yap
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Only PENDING orders can be matched");
        }

        // 2. İlgili asset'leri bul
        Asset asset = order.getAsset();
        Asset tryAsset = assetRepository.findAssetByCustomerAndName(
                order.getCustomer().getId(),
                "TRY"
        ).orElseThrow(() -> new RuntimeException("TRY asset not found"));

        // 3. Emir tipine göre işlem yap
        if (order.getOrderSide() == OrderSide.BUY) {
            processBuyOrder(order, asset, tryAsset);
        } else {
            processSellOrder(order, asset, tryAsset);
        }

        // 4. Order durumunu güncelle
        order.setStatus(OrderStatus.MATCHED);
        orderRepository.save(order);

        return convertToResponse(order);
    }

    private void processBuyOrder(Order order, Asset asset, Asset tryAsset) {
        // Hisse miktarını artır
        asset.setUsableSize(asset.getUsableSize() + order.getSize());
        assetRepository.save(asset);

        // TRY'den düşülen miktar zaten createOrder'da düşülmüştü (opsiyonel: ek kontrol)
    }

    private void processSellOrder(Order order, Asset asset, Asset tryAsset) {
        // TRY bakiyesini artır (satış geliri)
        BigDecimal totalValue = order.getPrice().multiply(BigDecimal.valueOf(order.getSize()));
        tryAsset.setUsableSize(tryAsset.getUsableSize() + totalValue.longValue());
        assetRepository.save(tryAsset);

        // Satılan hisse miktarı zaten createOrder'da düşülmüştü (opsiyonel: ek kontrol)
    }

    public void deleteOrder(Long customerId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus().equals(OrderStatus.CANCELED))
            throw new RuntimeException("Order already canceled");

        if (!order.getCustomer().getId().equals(1L)) {
            throw new RuntimeException("You are not authorized to delete this order");
        }

        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);

    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request, String username) {
        // 1. Müşteri ve Asset Validasyonu
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı!"));

        Asset asset = assetRepository.findAssetByCustomerAndName(customer.getId(), request.getAssetName())
                .orElseThrow(() -> new RuntimeException("Asset bulunamadı!"));

        // 2. Bakiye Kontrolü
        if (request.getSide() == OrderSide.BUY) {
            checkAndUpdateTryBalance(customer, request.getPrice(), request.getSize());
        } else { // SELL
            checkAndUpdateAssetBalance(asset, request.getSize());
        }

        // 3. Order Oluştur
        Order order = new Order();
        order.setCustomer(customer);
        order.setAsset(asset);
        order.setSize(request.getSize());
        order.setPrice(request.getPrice());
        order.setOrderSide(request.getSide());
        order.setStatus(OrderStatus.PENDING);
        order.setCreateDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        return convertToResponse(savedOrder);
    }

    private OrderResponse convertToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .assetName(order.getAsset().getAssetName()) // Asset adını çek
                .orderSide(order.getOrderSide().name()) // BUY/SELL
                .size(order.getSize())
                .price(order.getPrice())
                .status(order.getStatus().name()) // PENDING/MATCHED/CANCELED
                .createDate(order.getCreateDate())
                .customerId(order.getCustomer().getId())
                .build();
    }

    private void checkAndUpdateTryBalance(Customer customer, BigDecimal price, Long size) {
        Asset tryAsset = assetRepository.findAssetByCustomerAndName(customer.getId(), "TRY")
                .orElseThrow(() -> new RuntimeException("TRY varlığı bulunamadı!"));

        BigDecimal totalCost = price.multiply(BigDecimal.valueOf(size));
        if (tryAsset.getUsableSize() < totalCost.longValue()) {
            throw new RuntimeException("Yetersiz TRY bakiyesi!");
        }

        tryAsset.setUsableSize(tryAsset.getUsableSize() - totalCost.longValue());
        assetRepository.save(tryAsset);
    }

    private void checkAndUpdateAssetBalance(Asset asset, Long size) {
        if (asset.getUsableSize() < size) {
            throw new RuntimeException("Yetersiz hisse miktarı!");
        }
        asset.setUsableSize(asset.getUsableSize() - size);
        assetRepository.save(asset);
    }
}

