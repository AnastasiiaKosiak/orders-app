package com.teamvoy.order.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "creation_time")
    private LocalDateTime creationTIme;
    @ManyToOne
    private Item item;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "quantity")
    private Integer itemsQuantity;
}
