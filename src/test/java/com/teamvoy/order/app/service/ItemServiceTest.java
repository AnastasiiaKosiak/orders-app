package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.repository.ItemDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    private final ItemDao itemDao = Mockito.mock(ItemDao.class);
    private final ItemService itemService = new ItemServiceImpl(itemDao);

    @Test
    public void testAddItem_isOk() {
        Item expected = new Item();
        expected.setId(1L);
        expected.setItemName("apple");
        expected.setPrice(12.0);
        expected.setQuantity(1);
        expected.setTime(Instant.now());
        given(itemService.add(expected)).willReturn(expected);
        Item actual = itemService.add(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testAddAllItems_isOk() {
        Item item1 = new Item();
        item1.setId(1L);
        item1.setItemName("apple");
        item1.setPrice(12.0);
        item1.setQuantity(1);
        item1.setTime(Instant.now());
        List<Item> expected = List.of(item1);
        given(itemService.addAll(expected)).willReturn(expected);
        List<Item> actual = itemService.addAll(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllItems_isOk() {
        Item item = new Item();
        item.setId(3L);
        item.setItemName("apple");
        item.setPrice(12.0);
        item.setQuantity(1);
        item.setTime(Instant.now());
        itemService.add(item);
        List<Item> expected = List.of(item);
        given(itemService.getAll()).willReturn(expected);
        List<Item> actual = itemService.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void getCheapestItem_isOk() {
        Item item1 = new Item();
        item1.setId(4L);
        item1.setItemName("apple");
        item1.setPrice(12.0);
        item1.setQuantity(1);
        item1.setTime(Instant.now());
        Item item2 = new Item();
        item2.setId(5L);
        item2.setItemName("apple");
        item2.setPrice(6.0);
        item2.setQuantity(2);
        item2.setTime(Instant.now());
        itemService.addAll(List.of(item1, item2));
        List<Item> expected = List.of(item2);
        given(itemService.getCheapestProducts("apple", 1)).willReturn(expected);
        List<Item> actual = itemService.getCheapestProducts("apple", 1);
        assertEquals(expected, actual);
    }

    @Test
    public void getItemByName_isOk() {
        Item expected = new Item();
        expected.setId(20L);
        expected.setItemName("apple");
        expected.setPrice(12.0);
        expected.setQuantity(1);
        expected.setTime(Instant.now());
        itemService.add(expected);
        given(itemService.findByName("apple")).willReturn(expected);
        Item actual = itemService.findByName("apple");
        assertEquals(expected, actual);
    }
}