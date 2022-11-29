package com.oshkanov.drondispatcher.validator.cargo;

import java.util.List;

import com.oshkanov.drondispatcher.dto.CargoDto;
import com.oshkanov.drondispatcher.model.ValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@AllArgsConstructor
public class CargoValidatorCompositeService implements CargoValidator {

    private final List<CargoValidator> validators;

    @Override
    public ValidationResult validate(CargoDto cargoDto) {
        var validationResult = new ValidationResult();

        for (CargoValidator validator : validators) {
            var result = validator.validate(cargoDto);
            if (!result.isValid()) {
                validationResult.setValid(false);
                validationResult.getErrorMessages().addAll(result.getErrorMessages());
            }
        }

        return validationResult;
    }
}
