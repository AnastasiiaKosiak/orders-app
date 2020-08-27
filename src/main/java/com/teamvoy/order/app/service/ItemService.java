package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Item;
import java.util.List;

public interface ItemService {
    Item add(Item product);

    List<Item> addAll(List<Item> products);

    List<Item> getAll();

    List<Item> getCheapestProducts(String itemName, int quantity);

    Item findByName(String productName);
}
