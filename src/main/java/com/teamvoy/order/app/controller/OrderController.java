package com.teamvoy.order.app.controller;

import com.teamvoy.order.app.mapper.OrderMapper;
import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.model.dto.OrderRequestDto;
import com.teamvoy.order.app.model.dto.OrderResponseDto;
import com.teamvoy.order.app.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final static String CRON_EXPRESSION = "0 0/10 0 ? * * *";
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public OrderController(OrderMapper orderMapper,
                           OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    public void createOrder(@RequestBody OrderRequestDto requestDto) {
        Order newOrder = orderMapper.convertFromRequestDtoToOrder(requestDto);
        orderService.create(newOrder);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAll()
                .stream()
                .map(orderMapper::convertFromOrderToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        Order order = orderService.getById(Long.valueOf(id));
        if (isValid(order)) {
            orderService.delete(Long.valueOf(id));
        }
    }

    @Scheduled(cron = CRON_EXPRESSION)
    public void deleteInvalidOrdersOnSchedule() {
        List<Order> orders = orderService.getAll();
        for (Order order : orders) {
            orderService.delete(order.getId());
        }
    }

    private boolean isValid(Order order) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(order.getCreationTIme().plusMinutes(10));
    }
}
