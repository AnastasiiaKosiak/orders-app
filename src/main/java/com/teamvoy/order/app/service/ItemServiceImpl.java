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
        int totalCount = getTotalItemsCount(sortedItems);
        if (totalCount < quantity) {
            return sortedItems;
        }
        int itemIndex = 0;
        int currentQuantity = 0;
        for (Item item : sortedItems) {
            while (currentQuantity < quantity) {
                currentQuantity += item.getQuantity();
                itemIndex++;
            }
            break;
        }
        return sortedItems.subList(0, itemIndex);
    }

    @Override
    public Item findByName(String productName) {
        return itemDao.findFirstByItemName(productName);
    }

    private int getTotalItemsCount(List<Item> items) {
        return items.stream()
                .map(Item::getQuantity)
                .reduce(0, Integer::sum);
    }
}
