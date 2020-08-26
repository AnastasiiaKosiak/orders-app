package com.teamvoy.order.app.model.dto;

import lombok.Data;

@Data
public class ItemRequestDto {
    private Long id;
    private String itemName;
    private Double price;
    private int quantity;
}
