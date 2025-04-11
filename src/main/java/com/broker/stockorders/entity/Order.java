package com.broker.stockorders.entity;


import com.broker.stockorders.dto.model.OrderSide;
import com.broker.stockorders.dto.model.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "STOCK_SCHEMA", name = "orders", indexes = {
        @Index(columnList = "asset_id"),
        @Index(columnList = "customer_id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size")
    private Long size;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "order_side")
    @Enumerated(EnumType.STRING)
    private OrderSide orderSide;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setOrderSide(OrderSide orderSide) {
        this.orderSide = orderSide;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Long getId() {
        return this.id;
    }

    public Long getSize() {
        return this.size;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public OrderSide getOrderSide() {
        return this.orderSide;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Asset getAsset() {
        return this.asset;
    }
}