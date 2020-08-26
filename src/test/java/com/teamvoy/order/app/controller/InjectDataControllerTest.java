package com.teamvoy.order.app.controller;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.service.ItemService;
import com.teamvoy.order.app.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@AutoConfigureMockMvc
@ActiveProfiles("test")
class InjectDataControllerTest {
    private final ItemService itemService = Mockito.mock(ItemService.class);
    private final OrderService orderService = Mockito.mock(OrderService.class);

    @Test
    public void testAddingItems() {
        Item product1 = new Item(12.0, "apple", 10);
        Item product2 = new Item(7.5, "apple", 2);
        Item product3 = new Item(23.4, "orange", 5);
        itemService.addAll(List.of(product1, product2, product3));
        Mockito.when(itemService.getAll())
                .thenReturn(List.of(product1, product2, product3));
    }
}
