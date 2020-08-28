package com.teamvoy.order.app.service;

import com.teamvoy.order.app.model.Order;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface OrderService {
    String CRON_EXPRESSION = "0 0/10 0 ? * * * ";
    Order create(Order order);

    List<Order> getAll();

    Order getById(Long id);

    void delete(Long id);

    @Scheduled(cron = CRON_EXPRESSION)
    void deleteOnSchedule();
}
