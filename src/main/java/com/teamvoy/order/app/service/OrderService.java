package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.dto.OrderResponseDto;
import com.teamvoy.order.app.model.Order;
import java.util.List;

public interface OrderService {
    Order create(Order order);

    OrderResponseDto completeOrder(Order order);

    List<Order> getAll();

    Order getById(Long id);

    void delete(Long id);
}
