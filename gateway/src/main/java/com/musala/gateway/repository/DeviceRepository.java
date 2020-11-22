package com.musala.gateway.repository;

import com.musala.gateway.model.Device;
import com.musala.gateway.model.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByGateway(Gateway gateway);
}
