package com.teamvoy.order.app.controller;

import com.teamvoy.order.app.mapper.ItemMapper;
import com.teamvoy.order.app.model.dto.ItemRequestDto;
import com.teamvoy.order.app.model.dto.ItemResponseDto;
import com.teamvoy.order.app.service.ItemService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemMapper itemMapper;
    private final ItemService itemService;

    public ItemController(ItemMapper itemMapper,
                          ItemService itemService) {
        this.itemMapper = itemMapper;
        this.itemService = itemService;
    }

    @PostMapping
    public void add(@RequestBody ItemRequestDto itemRequestDto) {
        itemService.add(itemMapper.convertFromRequestDtoToProduct(itemRequestDto));
    }

    @GetMapping
    public List<ItemResponseDto> getAllProducts() {
        return itemService.getAll()
                .stream()
                .map(itemMapper::convertFromProductToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/cheapest/{itemName}")
    public List<ItemResponseDto> getCheapestItems(@PathVariable String itemName,
                                                  @RequestParam int quantity) {
        return itemService.getCheapestProducts(itemName, quantity)
                .stream()
                .map(itemMapper::convertFromProductToResponseDto)
                .collect(Collectors.toList());
    }
}
