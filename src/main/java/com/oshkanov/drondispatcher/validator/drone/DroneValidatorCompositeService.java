package com.oshkanov.drondispatcher.validator.drone;

import java.util.List;

import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.model.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class DroneValidatorCompositeService implements DroneValidator {
    private final List<DroneValidator> validators;

    @Override
    public ValidationResult validate(DroneDto droneDto) {
        var validationResult = new ValidationResult();

        for (DroneValidator validator : validators) {
            var result = validator.validate(droneDto);
            if (!result.isValid()) {
                validationResult.setValid(false);
                validationResult.getErrorMessages().addAll(result.getErrorMessages());
            }
        }

        return validationResult;
    }
}
