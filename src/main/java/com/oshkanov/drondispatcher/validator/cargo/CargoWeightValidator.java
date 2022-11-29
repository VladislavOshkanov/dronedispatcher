package com.oshkanov.drondispatcher.validator.cargo;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oshkanov.drondispatcher.dto.CargoDto;
import com.oshkanov.drondispatcher.model.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class CargoWeightValidator implements CargoValidator {

    @Override
    public ValidationResult validate(CargoDto cargoDto) {
        if (cargoDto.getWeight() < 1) {
            return new ValidationResult(false, List.of("Weight must be at least 1 g."));
        }

        return new ValidationResult(true, Collections.emptyList());
    }
}
