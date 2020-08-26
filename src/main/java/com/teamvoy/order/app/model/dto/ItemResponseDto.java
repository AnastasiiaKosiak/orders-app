package com.teamvoy.order.app.model.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemResponseDto {
    private Double price;
    private String itemName;
    private Integer quantity;
}
