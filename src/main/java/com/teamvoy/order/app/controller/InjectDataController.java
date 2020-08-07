package com.teamvoy.order.app.controller;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.service.ItemService;
import com.teamvoy.order.app.service.OrderService;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {
    private final ItemService itemService;
    private final OrderService orderService;

    public InjectDataController(ItemService itemService,
                                OrderService orderService) {
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @PostConstruct
    public void init() {
        addProducts();
        addOrders();
    }

    private void addProducts() {
        Item product1 = new Item(BigDecimal.valueOf(12.0), "apple", 10);
        Item product2 = new Item(BigDecimal.valueOf(7.5), "apple", 2);
        Item product3 = new Item(BigDecimal.valueOf(23.4), "orange", 5);
        itemService.addAll(List.of(product1, product2, product3));
    }

    private void addOrders() {
        Order order = new Order();
        order.setItem(itemService.findByName("orange"));
        order.setItemsQuantity(2);
        orderService.create(order);
    }
}
