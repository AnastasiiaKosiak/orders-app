package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Order;
import java.util.List;

public interface OrderService {
    Order create(Order order);

    List<Order> getAll();

    Order getById(Long id);

    void delete(Long id);
}
