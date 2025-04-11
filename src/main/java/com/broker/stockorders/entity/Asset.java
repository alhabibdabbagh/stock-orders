package com.broker.stockorders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(schema = "STOCK_SCHEMA", name = "asset")
@Getter
@Setter
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetName;   // Example: AAPL, TSLA, or TRY
    private Long size;          // Total owned
    private Long usableSize;    // Usable for trading

}