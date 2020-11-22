package com.musala.gateway.service;

import com.musala.gateway.exception.NotFoundException;
import com.musala.gateway.model.Gateway;
import com.musala.gateway.repository.GatewayRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GatewayService {
    private GatewayRepository gatewayRepository;
    private DeviceService deviceService;

    public Optional<Gateway> findById(Long id) {
        return gatewayRepository.findById(id);
    }

    public Page<Gateway> findAll(Pageable pageable) {
        return gatewayRepository.findAll(pageable);
    }

    public Gateway save(Gateway gateway) {
        return gatewayRepository.save(gateway);
    }

    public Gateway update(Gateway gateway) {
        Gateway existing = gatewayRepository.findById(gateway.getId()).orElseThrow(NotFoundException::new);
        return gatewayRepository.save(existing);
    }

    public void deleteById(Long id) {
        gatewayRepository.deleteById(id);
    }
}
