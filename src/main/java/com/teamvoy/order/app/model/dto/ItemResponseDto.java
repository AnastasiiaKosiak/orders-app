package com.teamvoy.order.app.model.dto;

import lombok.Data;

@Data
public class ItemResponseDto {
    private Long id;
    private Double price;
    private String itemName;
    private Integer quantity;
}
