package com.teamvoy.order.app.model.dto;

import java.math.BigDecimal;

public class ItemRequestDto {
    private String itemName;
    private BigDecimal price;
    private int quantity;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
