package com.teamvoy.order.app.model.dto;

import java.math.BigDecimal;

public class ItemResponseDto {
    private BigDecimal price;
    private String itemName;
    private Integer quantity;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
