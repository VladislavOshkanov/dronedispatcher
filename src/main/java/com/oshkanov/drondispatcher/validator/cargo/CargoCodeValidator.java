package com.oshkanov.drondispatcher.validator.cargo;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oshkanov.drondispatcher.dto.CargoDto;
import com.oshkanov.drondispatcher.model.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class CargoCodeValidator implements CargoValidator {

    private static final Pattern CARGO_CODE_REGEX = Pattern.compile("^[a-zA-Z0-9_-]*$");
    @Override
    public ValidationResult validate(CargoDto cargoDto) {
        if (cargoDto.getCode() == null || cargoDto.getCode().isEmpty()) {
            return new ValidationResult(false, List.of("Cargo code must not be null or empty"));
        }

        Matcher m = CARGO_CODE_REGEX.matcher(cargoDto.getCode());
        if (!m.matches()) {
            return new ValidationResult(false,
                    List.of("Cargo code must contain only capital letters, numbers, - and _ symbols"));
        }

        return new ValidationResult(true, Collections.emptyList());
    }
}
