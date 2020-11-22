package com.musala.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.gateway.model.Gateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class GatewayControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void gatewayList_shouldSucceed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/gateway/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isArray());
    }

    @Test
    void gatewayCreate_shouldSucceed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/gateway")
                .content(objectMapper.writeValueAsString(Gateway.builder()
                        .serialNo(UUID.randomUUID().toString())
                        .name("Gateway A")
                        .ip("192.168.0.2")
                        .build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
    }


}
