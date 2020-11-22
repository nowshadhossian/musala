package com.musala.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.gateway.model.Device;
import com.musala.gateway.model.DeviceStatus;
import com.musala.gateway.model.Gateway;
import com.musala.gateway.service.GatewayService;
import com.musala.gateway.utils.Constant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class DeviceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    GatewayService gatewayService;


    @Test
    void deviceList_shouldSucceed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/device/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isArray());
    }

    @Transactional
    @Test
    void deviceCreate_shouldSucceed() throws Exception {
        Gateway gateway = gatewayService.save(Gateway.builder()
                .ip("172.1.4.5")
                .name("Big G")
                .serialNo(UUID.randomUUID().toString())
                .build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/device")
                .content(objectMapper.writeValueAsString(Device.builder()
                        .status(DeviceStatus.ONLINE)
                        .vendor("Chinese brand")
                        .gateway(gateway)
                        .build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
    }

    @Transactional
    @Test
    void deviceCreate_11Peripheral_shouldNotValidate() throws Exception {
        Gateway gateway = gatewayService.save(Gateway.builder()
                .ip("175.1.4.1")
                .name("Small G")
                .serialNo(UUID.randomUUID().toString())
                .build());

        for(int i = 0; i < Constant.PERIPHERAL_LIMIT; i++) {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/device")
                    .content(objectMapper.writeValueAsString(Device.builder()
                            .status(DeviceStatus.ONLINE)
                            .vendor("Chinese brand")
                            .gateway(gateway)
                            .build()))
                    .contentType(MediaType.APPLICATION_JSON));
        }

       mockMvc.perform(MockMvcRequestBuilders.post("/api/device")
               .content(objectMapper.writeValueAsString(Device.builder()
                       .status(DeviceStatus.ONLINE)
                       .vendor("Chinese brand")
                       .gateway(gateway)
                       .build()))
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
