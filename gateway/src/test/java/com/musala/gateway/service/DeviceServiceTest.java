package com.musala.gateway.service;

import com.musala.gateway.exception.GatewayException;
import com.musala.gateway.model.Device;
import com.musala.gateway.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DeviceServiceTest {
    @Autowired DeviceService deviceService;

    @MockBean
    DeviceRepository deviceRepository;

    @Test
    public void findById_shouldReturn(){
        Mockito.when(deviceRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(Device.builder()
                .vendor("ABCD")
                .build()));
        assertEquals(deviceService.findById(55l).get().getVendor(), "ABCD");
    }

    @Test
    public void insert11Peripheral_shouldError(){
        Mockito.when(deviceRepository.findByGateway(Mockito.any()))
                .thenReturn(Arrays.asList(new Device[10]));

        assertThrows(GatewayException.class, () -> deviceService.insert(Device.builder()
                .vendor("dfds")
                .build()));
    }

}
