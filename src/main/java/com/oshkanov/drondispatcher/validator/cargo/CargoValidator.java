package com.oshkanov.drondispatcher.validator.cargo;

import com.oshkanov.drondispatcher.dto.CargoDto;
import com.oshkanov.drondispatcher.model.ValidationResult;

public interface CargoValidator {
    ValidationResult validate(CargoDto cargoDto);
}
