package com.musala.gateway.controller;

import com.musala.gateway.model.Gateway;
import com.musala.gateway.service.GatewayService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/gateway")
public class GatewayController {
    private GatewayService gatewayService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGatewayById(@PathVariable Long id) {
        return gatewayService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getGateways(Pageable pageable) {
        return ResponseEntity.ok(gatewayService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> saveGateway(@RequestBody Gateway form) {
        //validate gateway here and then save
        Gateway saved = gatewayService.save(Gateway.builder()
                .ip(form.getIp())
                .name(form.getName())
                .serialNo(form.getSerialNo())
                .build());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id) {
        return gatewayService.findById(id)
                .map(gateway -> ResponseEntity.ok(gatewayService.update(gateway)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Gateway gateway = gatewayService.findById(id).orElse(null);
        if (gateway == null) {
            return ResponseEntity.notFound().build();
        }
        gatewayService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
