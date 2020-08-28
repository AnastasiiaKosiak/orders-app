package com.teamvoy.order.app.repository;

import com.teamvoy.order.app.model.Order;
import java.util.List;

public interface OrderDao {
    Order create(Order order);

    List<Order> getAll();

    Order getById(Long id);

    void delete(Long id);

    boolean isNotValid(Order order);
}
