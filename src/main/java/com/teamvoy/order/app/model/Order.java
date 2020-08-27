package com.teamvoy.order.app.model;

import java.time.Instant;
import java.time.LocalDateTime;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "order")
public class Order {
    @Column(name = "time")
    private Instant time;
    @Column(name = "id")
    private Long id;
    @Column(name = "creation_time")
    private LocalDateTime creationTIme;
    private String itemName;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "quantity")
    private Integer itemsQuantity;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTIme() {
        return creationTIme;
    }

    public void setCreationTIme(LocalDateTime creationTIme) {
        this.creationTIme = creationTIme;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Integer itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }
}

