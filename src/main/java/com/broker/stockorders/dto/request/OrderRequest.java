package com.broker.stockorders.dto.request;

import com.broker.stockorders.dto.model.OrderSide;

import java.math.BigDecimal;


public class OrderRequest {
    private Long customerId;
    private String assetName;  // "AAPL", "TRY" gibi
    private OrderSide side;    // BUY veya SELL
    private Long size;        // İşlem miktarı
    private BigDecimal price; // Hisse başı fiyat

    public OrderRequest(Long customerId, String assetName, OrderSide side, Long size, BigDecimal price) {
        this.customerId = customerId;
        this.assetName = assetName;
        this.side = side;
        this.size = size;
        this.price = price;
    }

    public OrderRequest() {
    }

    public static OrderRequestBuilder builder() {
        return new OrderRequestBuilder();
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public OrderSide getSide() {
        return this.side;
    }

    public Long getSize() {
        return this.size;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderRequest)) return false;
        final OrderRequest other = (OrderRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$customerId = this.getCustomerId();
        final Object other$customerId = other.getCustomerId();
        if (this$customerId == null ? other$customerId != null : !this$customerId.equals(other$customerId))
            return false;
        final Object this$assetName = this.getAssetName();
        final Object other$assetName = other.getAssetName();
        if (this$assetName == null ? other$assetName != null : !this$assetName.equals(other$assetName)) return false;
        final Object this$side = this.getSide();
        final Object other$side = other.getSide();
        if (this$side == null ? other$side != null : !this$side.equals(other$side)) return false;
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $customerId = this.getCustomerId();
        result = result * PRIME + ($customerId == null ? 43 : $customerId.hashCode());
        final Object $assetName = this.getAssetName();
        result = result * PRIME + ($assetName == null ? 43 : $assetName.hashCode());
        final Object $side = this.getSide();
        result = result * PRIME + ($side == null ? 43 : $side.hashCode());
        final Object $size = this.getSize();
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        return result;
    }

    public String toString() {
        return "OrderRequest(customerId=" + this.getCustomerId() + ", assetName=" + this.getAssetName() + ", side=" + this.getSide() + ", size=" + this.getSize() + ", price=" + this.getPrice() + ")";
    }

    public static class OrderRequestBuilder {
        private Long customerId;
        private String assetName;
        private OrderSide side;
        private Long size;
        private BigDecimal price;

        OrderRequestBuilder() {
        }

        public OrderRequestBuilder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderRequestBuilder assetName(String assetName) {
            this.assetName = assetName;
            return this;
        }

        public OrderRequestBuilder side(OrderSide side) {
            this.side = side;
            return this;
        }

        public OrderRequestBuilder size(Long size) {
            this.size = size;
            return this;
        }

        public OrderRequestBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OrderRequest build() {
            return new OrderRequest(this.customerId, this.assetName, this.side, this.size, this.price);
        }

        public String toString() {
            return "OrderRequest.OrderRequestBuilder(customerId=" + this.customerId + ", assetName=" + this.assetName + ", side=" + this.side + ", size=" + this.size + ", price=" + this.price + ")";
        }
    }

    // Getter-Setter
}