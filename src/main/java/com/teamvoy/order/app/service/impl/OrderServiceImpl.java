package com.teamvoy.order.app.service.impl;

import com.teamvoy.order.app.model.dto.OrderResponseDto;
import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.repository.OrderRepository;
import com.teamvoy.order.app.service.ItemService;
import com.teamvoy.order.app.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    @Override
    public Order create(Order order) {
        order.setCreationTIme(LocalDateTime.now());
        order.setTotalPrice(order.getItem().getPrice()
                .multiply(BigDecimal.valueOf(order.getItemsQuantity())));
        return orderRepository.save(order);
    }

    public OrderResponseDto completeOrder(Order order) {
        Item item = itemService.findByName(order.getItem().getItemName());
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setItemName(item.getItemName());
        responseDto.setQuantity(item.getQuantity());
        responseDto.setPrice(item.getPrice()
                .multiply(BigDecimal.valueOf(responseDto.getQuantity())));
        item.setQuantity(0);
        itemService.update(item);
        save(responseDto);
        return responseDto;
    }

    private void save(OrderResponseDto dto) {
        Order order = new Order();
        order.setItem(itemService.findByName(dto.getItemName()));
        order.setItemsQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getPrice());
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
