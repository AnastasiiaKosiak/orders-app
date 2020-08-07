package com.teamvoy.order.app.model.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderResponseDto {
    private BigDecimal price;
    private Integer quantity;
    private String itemName;
}
