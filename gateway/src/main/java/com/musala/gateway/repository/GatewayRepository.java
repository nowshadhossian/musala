package com.musala.gateway.repository;

import com.musala.gateway.model.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
}
