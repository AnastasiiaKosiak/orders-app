package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.repository.ItemDao;
import com.teamvoy.order.app.repository.OrderDao;
import java.math.BigDecimal;
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
        Item item = itemRepository.findFirstByItemName(order.getItemName());
        BigDecimal totalPrice = BigDecimal.valueOf(item.getPrice())
                .multiply(BigDecimal.valueOf(order.getItemsQuantity()));
        order.setTotalPrice(totalPrice.doubleValue());
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

    @Override
    public void deleteOnSchedule() {
        List<Order> orders = orderRepository.getAll();
        for (Order order : orders) {
            if (orderRepository.isNotValid(order)) {
                delete(order.getId());
            }
        }
    }
}
