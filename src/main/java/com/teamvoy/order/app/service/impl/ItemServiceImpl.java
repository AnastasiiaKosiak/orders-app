package com.teamvoy.order.app.service.impl;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.repository.ItemRepository;
import com.teamvoy.order.app.service.ItemService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item add(Item product) {
        return itemRepository.save(product);
    }

    @Override
    public void addAll(List<Item> products) {
        itemRepository.saveAll(products);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public List<Item> getCheapestProducts(String itemName, int quantity) {
        List<Item> sortedItems = itemRepository.findCheapItems(itemName);
        return (quantity > sortedItems.size() - 1)
                ? sortedItems : sortedItems.subList(0, quantity);
    }

    @Override
    public Item update(Item product) {
        Item updateProduct = itemRepository.findById(product.getId()).get();
        updateProduct.setQuantity(product.getQuantity());
        return itemRepository.save(product);
    }

    @Override
    public Item findByName(String productName) {
        return itemRepository.findFirstByItemName(productName);
    }
}
