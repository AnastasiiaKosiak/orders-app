package com.teamvoy.order.app.model.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Double price;
    private Integer quantity;
    private String itemName;
}
