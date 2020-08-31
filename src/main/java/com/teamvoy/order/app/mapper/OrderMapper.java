package com.teamvoy.order.app.mapper;

import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.model.dto.OrderRequestDto;
import com.teamvoy.order.app.model.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order convertFromRequestDtoToOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setId(orderRequestDto.getId());
        order.setItemName(orderRequestDto.getItemName());
        order.setItemsQuantity(orderRequestDto.getQuantity());
        return order;
    }

    public OrderResponseDto convertFromOrderToResponseDto(Order order) {
        //        OrderResponseDto responseDto = new OrderResponseDto();
        //        responseDto.setId(order.getId());
        //        responseDto.setPrice(order.getTotalPrice());
        //        responseDto.setItemName(order.getItemName());
        //        responseDto.setQuantity(order.getItemsQuantity());
        //        return responseDto;
        return new OrderResponseDto();
    }
}
