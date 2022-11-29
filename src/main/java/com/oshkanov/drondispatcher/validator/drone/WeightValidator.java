package com.oshkanov.drondispatcher.validator.drone;

import java.util.Collections;
import java.util.List;

import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.model.ValidationResult;
import org.springframework.stereotype.Service;

@Service
public class WeightValidator implements DroneValidator {
    @Override
    public ValidationResult validate(DroneDto droneDto) {
        if (droneDto.getWeight() <= 0 || droneDto.getWeight() > 500) {
            return new ValidationResult(false, List.of("Weight must be between 1 and 500 g"));
        }

        return new ValidationResult(true, Collections.emptyList());
    }
}
