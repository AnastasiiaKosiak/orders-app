package com.teamvoy.order.app.model.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemResponseDto {
    private BigDecimal price;
    private String itemName;
    private Integer quantity;
}
