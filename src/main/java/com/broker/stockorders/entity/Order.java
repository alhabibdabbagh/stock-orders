package com.broker.stockorders.entity;


import com.broker.stockorders.dto.model.OrderSide;
import com.broker.stockorders.dto.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "STOCK_SCHEMA",name = "orders", indexes = {
        @Index(columnList = "asset_id"),
        @Index(columnList = "customer_id")
})
@Getter
@Setter
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
}