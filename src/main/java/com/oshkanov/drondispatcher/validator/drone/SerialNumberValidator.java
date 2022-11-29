package com.oshkanov.drondispatcher.validator.drone;

import java.util.Collections;
import java.util.List;

import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.model.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class SerialNumberValidator implements DroneValidator {
    @Override
    public ValidationResult validate(DroneDto droneDto) {
        if (droneDto.getSerialNumber() == null || droneDto.getSerialNumber().isEmpty()) {
            return new ValidationResult(false, List.of("Serial number must not be empty"));
        }

        if (droneDto.getSerialNumber().length() > 100) {
            return new ValidationResult(false, List.of("Serial number must has 100 characters maximum"));
        }

        return new ValidationResult(true, Collections.emptyList());

    }
}
