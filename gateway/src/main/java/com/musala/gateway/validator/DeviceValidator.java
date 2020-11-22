package com.musala.gateway.validator;

import com.musala.gateway.model.Device;
import com.musala.gateway.service.DeviceService;
import com.musala.gateway.service.GatewayService;
import com.musala.gateway.utils.Constant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class DeviceValidator implements Validator {
    private final DeviceService deviceService;
    private final GatewayService gatewayService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Device.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Device device = (Device) target;
        if(!gatewayService.findById(device.getGateway().getId()).isPresent()){
            errors.rejectValue("gateway", "","Gateway not found");
        }

        if(deviceService.hasPeripheralLimitReached(device)){
            errors.rejectValue("gateway", "", "Cannot have more than "
                    .concat(String.valueOf(Constant.PERIPHERAL_LIMIT))
                    .concat(" devices"));
        }
    }
}
