package com.teamvoy.order.app.mapper;

import com.teamvoy.order.app.model.dto.ItemRequestDto;
import com.teamvoy.order.app.model.dto.ItemResponseDto;
import com.teamvoy.order.app.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemResponseDto convertFromProductToResponseDto(Item item) {
        ItemResponseDto responseDto = new ItemResponseDto();
        responseDto.setQuantity(item.getQuantity());
        responseDto.setItemName(item.getItemName());
        responseDto.setPrice(item.getPrice());
        return responseDto;
    }

    public Item convertFromRequestDtoToProduct(ItemRequestDto requestDto) {
        Item item = new Item();
        item.setPrice(requestDto.getPrice());
        item.setQuantity(requestDto.getQuantity());
        item.setItemName(requestDto.getItemName());
        return item;
    }
}
