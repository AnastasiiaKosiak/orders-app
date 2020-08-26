package com.teamvoy.order.app.model;

import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Data
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
}

