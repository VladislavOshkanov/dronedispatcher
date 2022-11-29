package com.oshkanov.drondispatcher.validator.drone;

import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.model.ValidationResult;

public interface DroneValidator {
    ValidationResult validate(DroneDto droneDto);
}
