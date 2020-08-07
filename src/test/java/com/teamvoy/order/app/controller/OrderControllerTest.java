package com.teamvoy.order.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamvoy.order.app.mapper.OrderMapper;
import com.teamvoy.order.app.model.dto.OrderRequestDto;
import com.teamvoy.order.app.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;
    @MockBean
    private OrderMapper orderMapper;

    @Test
    public void testCreateOrder_isOk() throws Exception {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setItemName("apple");
        requestDto.setQuantity(2);
        mvc.perform(post("/orders")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());
    }
}