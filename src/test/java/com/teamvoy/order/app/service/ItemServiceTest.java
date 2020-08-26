package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.repository.ItemDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    private final ItemDao itemDao = Mockito.mock(ItemDao.class);
    private final ItemService itemService = new ItemServiceImpl(itemDao);
    private Item item;

    @BeforeEach
    public void setUp() {
        item = new Item();
        item.setId(1L);
        item.setItemName("apple");
        item.setPrice(12.0);
        item.setQuantity(1);
        item.setTime(Instant.now());
    }

    @Test
    public void testAddItem_isOk() {
        given(itemService.add(item)).willReturn(item);
        Item actual = itemService.add(item);
        assertEquals(item, actual);
    }

    @Test
    public void testAddAllItems_isOk() {
        List<Item> expected = List.of(item);
        given(itemService.addAll(expected)).willReturn(expected);
        List<Item> actual = itemService.addAll(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllItems_isOk() {
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
        itemService.add(item);
        given(itemService.findByName("apple")).willReturn(item);
        Item actual = itemService.findByName("apple");
        assertEquals(item, actual);
    }
}
