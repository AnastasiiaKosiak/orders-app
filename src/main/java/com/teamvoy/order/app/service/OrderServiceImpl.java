package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.repository.ItemDao;
import com.teamvoy.order.app.repository.OrderDao;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderRepository;
    private final ItemDao itemRepository;

    public OrderServiceImpl(OrderDao orderRepository, ItemDao itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Order create(Order order) {
        order.setCreationTIme(LocalDateTime.now());
        Item orderItem = itemRepository.findFirstByItemName(order.getItemName());
        order.setTotalPrice(orderItem.getPrice() * (order.getItemsQuantity()));
        return orderRepository.create(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
