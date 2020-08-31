package com.teamvoy.order.app.mapper;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.model.dto.ItemRequestDto;
import com.teamvoy.order.app.model.dto.ItemResponseDto;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemResponseDto convertFromProductToResponseDto(Item item) {
        ItemResponseDto responseDto = new ItemResponseDto();
        responseDto.setId(item.getId());
        responseDto.setQuantity(item.getQuantity());
        responseDto.setItemName(item.getItemName());
        responseDto.setPrice(item.getPrice());
        responseDto.setTotalPrice(BigDecimal.valueOf(item.getPrice())
                .multiply(BigDecimal.valueOf(item.getQuantity()))
                .doubleValue());
        return responseDto;
    }

    public Item convertFromRequestDtoToProduct(ItemRequestDto requestDto) {
        Item item = new Item();
        item.setId(requestDto.getId());
        item.setPrice(requestDto.getPrice().doubleValue());
        item.setQuantity(requestDto.getQuantity());
        item.setItemName(requestDto.getItemName());
        return item;
    }
}
