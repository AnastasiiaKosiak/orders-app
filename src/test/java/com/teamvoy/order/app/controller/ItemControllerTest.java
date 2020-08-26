package com.teamvoy.order.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamvoy.order.app.mapper.ItemMapper;
import com.teamvoy.order.app.mapper.OrderMapper;
import com.teamvoy.order.app.model.dto.ItemRequestDto;
import com.teamvoy.order.app.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@WebMvcTest(controllers = ItemController.class)
class ItemControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ItemMapper itemMapper;
    @MockBean
    private ItemService itemService;

    @Test
    public void testCreateItem_isOk() throws Exception {
        ItemRequestDto requestDto = new ItemRequestDto();
        requestDto.setItemName("apple");
        requestDto.setId(3L);
        requestDto.setQuantity(2);
        requestDto.setPrice(23.0);
        mvc.perform(post("/items")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllItems_isOk() throws Exception {
        mvc.perform(get("/items")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCheapestItems_isOk() throws Exception {
        mvc.perform(get("/items/cheapest/{itemName}", "apple")
                .contentType("application/json")
                .param("quantity", "1"))
                .andExpect(status().isOk());
    }
}
