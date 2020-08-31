package com.teamvoy.order.app.controller;

import com.teamvoy.order.app.mapper.ItemMapper;
import com.teamvoy.order.app.model.dto.ItemRequestDto;
import com.teamvoy.order.app.model.dto.ItemResponseDto;
import com.teamvoy.order.app.service.ItemService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<?> add(@RequestBody ItemRequestDto itemRequestDto) {
        itemService.add(itemMapper.convertFromRequestDtoToProduct(itemRequestDto));
        URI location = URI.create("/items");
        return ResponseEntity.created(location)
                .header("Response header")
                .body("Item created");
    }

    @GetMapping
    public List<ItemResponseDto> getAllItems() {
        return itemService.getAll()
                .stream()
                .map(itemMapper::convertFromProductToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/cheapest/{itemName}/{quantity}")
    public List<ItemResponseDto> getCheapestItems(@PathVariable String itemName,
                                                  @PathVariable int quantity) {
        return itemService.getCheapestProducts(itemName, quantity)
                .stream()
                .map(itemMapper::convertFromProductToResponseDto)
                .collect(Collectors.toList());
    }
}
