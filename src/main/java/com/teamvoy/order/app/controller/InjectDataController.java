package com.teamvoy.order.app.controller;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.service.ItemService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {
    private final ItemService itemService;

    public InjectDataController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostConstruct
    public void init() {
        addProducts();
    }

    private void addProducts() {
        Item product1 = new Item(12.0, "apple", 10);
        product1.setId(1L);
        itemService.addAll(List.of(product1));
    }
}
