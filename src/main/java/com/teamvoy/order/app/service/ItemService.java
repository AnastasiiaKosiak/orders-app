package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Item;
import java.util.List;

public interface ItemService {
    Item add(Item product);

    void addAll(List<Item> products);

    List<Item> getAll();

    Item getById(Long id);

    List<Item> getCheapestProducts(String itemName, int quantity);

    Item update(Item product);

    Item findByName(String productName);
}
