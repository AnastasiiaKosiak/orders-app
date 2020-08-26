package com.teamvoy.order.app.model.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private Long id;
    private String itemName;
    private Integer quantity;
}
