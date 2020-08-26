package com.teamvoy.order.app.model.dto;

import lombok.Data;

@Data
public class ItemResponseDto {
    private Double price;
    private String itemName;
    private Integer quantity;
}
