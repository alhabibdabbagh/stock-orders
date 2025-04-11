package com.broker.stockorders.dto.response;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {

    private Long id;
    private Long size;
    private BigDecimal price;
    private String orderSide;
    private String status;
    private LocalDateTime createDate;

    private Long customerId;
    private Long assetId;
}
