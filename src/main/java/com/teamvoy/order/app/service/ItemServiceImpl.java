package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.repository.ItemDao;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public Item add(Item product) {
        return itemDao.save(product);
    }

    @Override
    public List<Item> addAll(List<Item> products) {
        return itemDao.saveAll(products);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.findAll();
    }

    @Override
    public List<Item> getCheapestProducts(String itemName, int quantity) {
        List<Item> sortedItems = itemDao.findCheapItems(itemName);
        return (quantity > sortedItems.size() - 1)
                ? sortedItems : sortedItems.subList(0, quantity);
    }

    @Override
    public Item findByName(String productName) {
        return itemDao.findFirstByItemName(productName);
    }
}
