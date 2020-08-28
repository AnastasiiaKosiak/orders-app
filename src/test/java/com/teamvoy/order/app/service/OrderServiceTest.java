package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.repository.ItemDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private final OrderService orderService = Mockito.mock(OrderService.class);
    private final ItemDao itemDao = Mockito.mock(ItemDao.class);
    private Order order;
    private Item item;

    @BeforeEach
    public void setUp() {
        Item item = new Item();
        item.setId(1L);
        item.setItemName("apple");
        item.setPrice(12.0);
        item.setQuantity(1);
        item.setTime(Instant.now());
        Order order = new Order();
        order.setId(2L);
        order.setItemsQuantity(1);
        order.setItemName("apple");
    }

    @Test
    public void testAddOrder_isOk() {
        itemDao.save(item);
        given(orderService.create(order)).willReturn(order);
        Order actual = orderService.create(order);
        assertEquals(order, actual);
    }

    @Test
    public void deleteOrderById_isOk() {
        itemDao.save(item);
        orderService.create(order);
        orderService.delete(1L);
        int expectedSize = 0;
        assertEquals(expectedSize, orderService.getAll().size());
    }

    @Test
    public void getOrderById_isOk() {
        itemDao.save(item);
        orderService.create(order);
        Order actual = orderService.getById(1L);
        assertEquals(order, actual);
    }
}
