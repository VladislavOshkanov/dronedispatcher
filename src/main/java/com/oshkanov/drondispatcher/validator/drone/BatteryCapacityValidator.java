package com.oshkanov.drondispatcher.validator.drone;

import java.util.Collections;
import java.util.List;

import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.model.ValidationResult;
import liquibase.pro.packaged.V;
import org.springframework.stereotype.Service;

@Service
public class BatteryCapacityValidator implements DroneValidator {
    @Override
    public ValidationResult validate(DroneDto droneDto) {
        if (droneDto.getBatteryCapacity() < 0 || droneDto.getBatteryCapacity() > 100) {
            return new ValidationResult(false,
                    List.of("Battery capacity should be between 0 and 100 percent"));
        }

        return new ValidationResult(true, Collections.emptyList());
    }
}
