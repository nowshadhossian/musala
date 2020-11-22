package com.musala.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.gateway.model.Gateway;
import com.musala.gateway.service.GatewayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GatewayControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean GatewayService gatewayService;

    @Test
    void gatewayById_shouldReturn() throws Exception {
        when(gatewayService.findById(1l)).thenReturn(java.util.Optional.ofNullable(Gateway.builder()
                .name("RTCL")
                .id(1l)
                .build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/gateway/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("RTCL"));
    }

}
