package com.teamvoy.order.app.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "items")
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private String itemName;

    public Item() {

    }

    public Item(BigDecimal price, String itemName, Integer quantity) {
        this.price = price;
        this.quantity = quantity;
        this.itemName = itemName;
    }
}



