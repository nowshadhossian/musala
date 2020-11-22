package com.musala.gateway.controller;

import com.musala.gateway.exception.NotFoundException;
import com.musala.gateway.model.Device;
import com.musala.gateway.model.Gateway;
import com.musala.gateway.service.DeviceService;
import com.musala.gateway.service.GatewayService;
import com.musala.gateway.validator.DeviceValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/device")
public class DeviceController {
    private DeviceService deviceService;
    private DeviceValidator deviceValidator;
    private GatewayService gatewayService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id) {
        return deviceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list/{gatewayId}")
    public ResponseEntity<?> getDevices(@PathVariable Long gatewayId) {
        Gateway gateway = gatewayService.findById(gatewayId).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(deviceService.findByGateway(gateway));
    }

    @PostMapping
    public ResponseEntity<?> saveDevice(@RequestBody Device form, BindingResult bindingResult) {
        deviceValidator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Device saved = deviceService.insert(Device.builder()
                .status(form.getStatus())
                .vendor(form.getVendor())
                .gateway(form.getGateway())
                .status(form.getStatus())
                .build());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Device form) {
        return deviceService.findById(id)
                .map(device -> ResponseEntity.ok(deviceService.update(form)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Device device = deviceService.findById(id).orElse(null);
        if (device == null) {
            return ResponseEntity.notFound().build();
        }
        deviceService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
