package com.broker.stockorders.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponse {

    private Long id;
    private Long size;
    private BigDecimal price;
    private String orderSide;
    private String status;
    private LocalDateTime createDate;
    private String assetName;

    private Long customerId;
    private Long assetId;

    public OrderResponse(Long id, Long size, BigDecimal price, String orderSide, String status, LocalDateTime createDate, String assetName, Long customerId, Long assetId) {
        this.id = id;
        this.size = size;
        this.price = price;
        this.orderSide = orderSide;
        this.status = status;
        this.createDate = createDate;
        this.assetName = assetName;
        this.customerId = customerId;
        this.assetId = assetId;
    }

    public OrderResponse() {
    }

    public static OrderResponseBuilder builder() {
        return new OrderResponseBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderResponse)) return false;
        final OrderResponse other = (OrderResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$orderSide = this.getOrderSide();
        final Object other$orderSide = other.getOrderSide();
        if (this$orderSide == null ? other$orderSide != null : !this$orderSide.equals(other$orderSide)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$createDate = this.getCreateDate();
        final Object other$createDate = other.getCreateDate();
        if (this$createDate == null ? other$createDate != null : !this$createDate.equals(other$createDate))
            return false;
        final Object this$assetName = this.getAssetName();
        final Object other$assetName = other.getAssetName();
        if (this$assetName == null ? other$assetName != null : !this$assetName.equals(other$assetName)) return false;
        final Object this$customerId = this.getCustomerId();
        final Object other$customerId = other.getCustomerId();
        if (this$customerId == null ? other$customerId != null : !this$customerId.equals(other$customerId))
            return false;
        final Object this$assetId = this.getAssetId();
        final Object other$assetId = other.getAssetId();
        if (this$assetId == null ? other$assetId != null : !this$assetId.equals(other$assetId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $size = this.getSize();
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $orderSide = this.getOrderSide();
        result = result * PRIME + ($orderSide == null ? 43 : $orderSide.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $createDate = this.getCreateDate();
        result = result * PRIME + ($createDate == null ? 43 : $createDate.hashCode());
        final Object $assetName = this.getAssetName();
        result = result * PRIME + ($assetName == null ? 43 : $assetName.hashCode());
        final Object $customerId = this.getCustomerId();
        result = result * PRIME + ($customerId == null ? 43 : $customerId.hashCode());
        final Object $assetId = this.getAssetId();
        result = result * PRIME + ($assetId == null ? 43 : $assetId.hashCode());
        return result;
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

    public String getOrderSide() {
        return this.orderSide;
    }

    public String getStatus() {
        return this.status;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public Long getAssetId() {
        return this.assetId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setOrderSide(String orderSide) {
        this.orderSide = orderSide;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String toString() {
        return "OrderResponse(id=" + this.getId() + ", size=" + this.getSize() + ", price=" + this.getPrice() + ", orderSide=" + this.getOrderSide() + ", status=" + this.getStatus() + ", createDate=" + this.getCreateDate() + ", assetName=" + this.getAssetName() + ", customerId=" + this.getCustomerId() + ", assetId=" + this.getAssetId() + ")";
    }

    public static class OrderResponseBuilder {
        private Long id;
        private Long size;
        private BigDecimal price;
        private String orderSide;
        private String status;
        private LocalDateTime createDate;
        private String assetName;
        private Long customerId;
        private Long assetId;

        OrderResponseBuilder() {
        }

        public OrderResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderResponseBuilder size(Long size) {
            this.size = size;
            return this;
        }

        public OrderResponseBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OrderResponseBuilder orderSide(String orderSide) {
            this.orderSide = orderSide;
            return this;
        }

        public OrderResponseBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderResponseBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public OrderResponseBuilder assetName(String assetName) {
            this.assetName = assetName;
            return this;
        }

        public OrderResponseBuilder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public OrderResponseBuilder assetId(Long assetId) {
            this.assetId = assetId;
            return this;
        }

        public OrderResponse build() {
            return new OrderResponse(this.id, this.size, this.price, this.orderSide, this.status, this.createDate, this.assetName, this.customerId, this.assetId);
        }

        public String toString() {
            return "OrderResponse.OrderResponseBuilder(id=" + this.id + ", size=" + this.size + ", price=" + this.price + ", orderSide=" + this.orderSide + ", status=" + this.status + ", createDate=" + this.createDate + ", assetName=" + this.assetName + ", customerId=" + this.customerId + ", assetId=" + this.assetId + ")";
        }
    }
}
