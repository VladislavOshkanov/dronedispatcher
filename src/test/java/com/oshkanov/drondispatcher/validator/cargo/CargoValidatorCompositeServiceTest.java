package com.oshkanov.drondispatcher.validator.cargo;

import java.util.List;

import com.oshkanov.drondispatcher.dto.CargoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CargoValidatorCompositeServiceTest {

    private CargoValidatorCompositeService uut = new CargoValidatorCompositeService(List.of(
            new CargoNameValidator(),
            new CargoCodeValidator(),
            new CargoWeightValidator())
    );

    @Test
    void validationSucceeded() {
        CargoDto cargoDto = new CargoDto();

        cargoDto.setName("Aspirin");
        cargoDto.setCode("abcd-12345_44");
        cargoDto.setWeight(400);

        assertTrue(uut.validate(cargoDto).isValid());
    }

    @Test
    void validationFailedWrongName() {
        CargoDto cargoDto = new CargoDto();

        cargoDto.setName("Aspir$##$in");
        cargoDto.setCode("abcd-12345_44");
        cargoDto.setWeight(400);

        assertFalse(uut.validate(cargoDto).isValid());
    }

    @Test
    void validationFailedWrongCode() {
        CargoDto cargoDto = new CargoDto();

        cargoDto.setName("Aspirin");
        cargoDto.setCode("abcd-12$$$345_44");
        cargoDto.setWeight(400);

        assertFalse(uut.validate(cargoDto).isValid());
    }

    @Test
    void validationFailedWrongWeight() {
        CargoDto cargoDto = new CargoDto();

        cargoDto.setName("Aspirin");
        cargoDto.setCode("abcd-12345_44");
        cargoDto.setWeight(0);

        assertFalse(uut.validate(cargoDto).isValid());
    }
}
