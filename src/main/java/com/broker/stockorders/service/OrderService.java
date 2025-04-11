package com.broker.stockorders.service;

import com.broker.stockorders.dto.response.OrderResponse;
import com.broker.stockorders.entity.Customer;
import com.broker.stockorders.entity.Order;
import com.broker.stockorders.repository.CustomerRepository;
import com.broker.stockorders.repository.OrderRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
//@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<OrderResponse> getOrdersByCustomerId(Long customerId) {
        // Customer'ı bulma
        Customer customer = customerRepository.findByUsername("ahmet")
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Order'ları almak ve maplemek

        return customer.getOrderList().stream()
                .map(order -> OrderResponse.builder()
                        .orderSide(order.getOrderSide() != null ? order.getOrderSide().name() : "UNKNOWN")  // Null kontrolü ekledik
                        .id(order.getId())
                        .size(order.getSize())
                        .price(order.getPrice())
                        .status(order.getStatus() != null ? order.getStatus().name() : "UNKNOWN")  // Null kontrolü ekledik
                        .createDate(order.getCreateDate())
                        .customerId(order.getCustomer() != null ? order.getCustomer().getId() : null)
                        .assetId(order.getAsset() != null ? order.getAsset().getId() : null)
                        .build())  // OrderResponse oluştur
                .collect(Collectors.toList());  // Map'lenmiş OrderResponse listesi döndür
    }
}


/*
@Service
@RequiredArgsConstructor
public class OrderService {

    private  OrderRepository orderRepository;
    private  AssetRepository assetRepository;

*/
/*    public Order createOrder(Customer customer, Asset asset, OrderSide side, Long size, BigDecimal price) {
        // TRY ve usable size kontrolü burada yapılır
        Asset tryAsset = assetRepository.findByCustomerIdAndAssetName(customer.getId(), "TRY");
        if (side == OrderSide.BUY) {
            BigDecimal total = price.multiply(BigDecimal.valueOf(size));
            if (tryAsset.getUsableSize() < total.longValue()) {
                throw new RuntimeException("Yetersiz TRY bakiyesi");
            }
            tryAsset.setUsableSize(tryAsset.getUsableSize() - total.longValue());
        } else if (side == OrderSide.SELL) {
            if (asset.getUsableSize() < size) {
                throw new RuntimeException("Yetersiz varlık");
            }
            asset.setUsableSize(asset.getUsableSize() - size);
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setAsset(asset);
        order.setSize(size);
        order.setPrice(price);
        order.setOrderSide(side);
        order.setStatus(OrderStatus.PENDING);
        order.setCreateDate(LocalDateTime.now());

        assetRepository.save(asset);
        assetRepository.save(tryAsset);
        return orderRepository.save(order);
    }

    public Page<Order> listOrders(Customer customer, LocalDateTime from, LocalDateTime to, Pageable pageable) {
        return orderRepository.findByCustomerAndCreateDateBetween(customer, from, to, pageable);
    }

    public void cancelOrder(Order order) {
        if (!OrderStatus.PENDING.equals(order.getStatus())) {
            throw new RuntimeException("Yalnızca pending order iptal edilebilir");
        }

        order.setStatus(OrderStatus.CANCELED);

        if (OrderSide.BUY.equals(order.getOrderSide())) {
            Asset tryAsset = assetRepository.findByCustomerIdAndAssetName(order.getCustomer().getId(), "TRY");
            BigDecimal refund = order.getPrice().multiply(BigDecimal.valueOf(order.getSize()));
            tryAsset.setUsableSize(tryAsset.getUsableSize() + refund.longValue());
            assetRepository.save(tryAsset);
        } else {
            Asset asset = order.getAsset();
            asset.setUsableSize(asset.getUsableSize() + order.getSize());
            assetRepository.save(asset);
        }

        orderRepository.save(order);
    }

    public void matchOrder(Order order) {
        if (!OrderStatus.PENDING.equals(order.getStatus())) {
            throw new RuntimeException("Sadece pending order match yapılabilir");
        }

        Asset asset = order.getAsset();
        Asset tryAsset = assetRepository.findByCustomerIdAndAssetName(order.getCustomer().getId(), "TRY");

        if (OrderSide.BUY.equals(order.getOrderSide())) {
            asset.setSize(asset.getSize() + order.getSize());
            asset.setUsableSize(asset.getUsableSize() + order.getSize());
        } else {
            BigDecimal gain = order.getPrice().multiply(BigDecimal.valueOf(order.getSize()));
            tryAsset.setSize(tryAsset.getSize() + gain.longValue());
            tryAsset.setUsableSize(tryAsset.getUsableSize() + gain.longValue());
        }

        order.setStatus(OrderStatus.MATCHED);
        assetRepository.save(asset);
        assetRepository.save(tryAsset);
        orderRepository.save(order);
    }*//*

}*/
