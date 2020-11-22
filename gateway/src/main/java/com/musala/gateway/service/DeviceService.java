package com.musala.gateway.service;

import com.musala.gateway.exception.GatewayException;
import com.musala.gateway.exception.NotFoundException;
import com.musala.gateway.model.Device;
import com.musala.gateway.model.Gateway;
import com.musala.gateway.repository.DeviceRepository;
import com.musala.gateway.utils.Constant;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DeviceService {
    private DeviceRepository deviceRepository;

    public Optional<Device> findById(Long id) {
        return deviceRepository.findById(id);
    }

    public Page<Device> findAll(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    public Device insert(Device device) {
        if(hasPeripheralLimitReached(device)){
            throw new GatewayException("Gateway cannot have more than "
                    .concat(String.valueOf(Constant.PERIPHERAL_LIMIT))
                    .concat(" devices"));
        }
        return deviceRepository.save(device);
    }

    public boolean hasPeripheralLimitReached(Device device){
        List<Device> devices = findByGateway(device.getGateway());
        return devices.size() >= Constant.PERIPHERAL_LIMIT;
    }

    private Device save(Device device) {
        return deviceRepository.save(device);
    }

    public Device update(Device device) {
        Device existing = deviceRepository.findById(device.getId()).orElseThrow(NotFoundException::new);
        return save(existing);
    }

    public void deleteById(Long id) {
        deviceRepository.deleteById(id);
    }

    public List<Device> findByGateway(Gateway gateway) {
        return deviceRepository.findByGateway(gateway);
    }
}
