package com.oshkanov.drondispatcher.validator.cargo;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oshkanov.drondispatcher.dto.CargoDto;
import com.oshkanov.drondispatcher.model.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class CargoNameValidator implements CargoValidator {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]*$");
    @Override
    public ValidationResult validate(CargoDto cargoDto) {
        if (cargoDto.getName() == null || cargoDto.getName().isEmpty()) {
            return new ValidationResult(false, List.of("Cargo name should not be null or empty"));
        }

        Matcher m = NAME_PATTERN.matcher(cargoDto.getName());
        if (!m.matches()) {
            return new ValidationResult(false,
                    List.of("Cargo name should only contain digits, letters, - and _ symbols"));
        }

        return new ValidationResult(true, Collections.emptyList());
    }
}
