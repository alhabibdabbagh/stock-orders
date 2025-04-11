package com.broker.stockorders.entity;

import jakarta.persistence.*;

@Entity
@Table(schema = "STOCK_SCHEMA", name = "asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetName;   // Example: AAPL, TSLA, or TRY
    private Long size;          // Total owned
    private Long usableSize;    // Usable for trading

    public Long getId() {
        return this.id;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public Long getSize() {
        return this.size;
    }

    public Long getUsableSize() {
        return this.usableSize;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setUsableSize(Long usableSize) {
        this.usableSize = usableSize;
    }
}