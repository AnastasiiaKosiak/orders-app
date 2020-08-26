package com.teamvoy.order.app.model;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Data
@Measurement(name = "item")
public class Item {
    @Column(name = "time")
    private Instant time;
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "itemName")
    private String itemName;

    public Item() {
    }

    public Item(Double price, String itemName, Integer quantity) {
        this.price = price;
        this.quantity = quantity;
        this.itemName = itemName;
    }
}



