package com.teamvoy.order.app.model.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemRequestDto {
    private String itemName;
    private BigDecimal price;
    private int quantity;
}
