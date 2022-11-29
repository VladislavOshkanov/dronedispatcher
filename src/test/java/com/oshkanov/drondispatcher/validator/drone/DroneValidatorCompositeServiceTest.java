package com.oshkanov.drondispatcher.validator.drone;

import java.util.List;

import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.model.DroneModel;
import com.oshkanov.drondispatcher.model.DroneState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneValidatorCompositeServiceTest {

    private DroneValidatorCompositeService uut =
            new DroneValidatorCompositeService(List.of(
                    new BatteryCapacityValidator(),
                    new SerialNumberValidator(),
                    new WeightValidator())
            );

    @Test
    void validationSucceed() {
        var droneDto = new DroneDto();
        droneDto.setSerialNumber("abcd");
        droneDto.setWeight(400);
        droneDto.setBatteryCapacity(100);
        droneDto.setModel(DroneModel.Lightweight);
        droneDto.setState(DroneState.LOADED);

        assertTrue(uut.validate(droneDto).isValid());
    }

    @Test
    void validationFailedWrongSerialNumber() {
        var droneDto = new DroneDto();

        droneDto.setSerialNumber("a".repeat(200));
        droneDto.setWeight(400);
        droneDto.setBatteryCapacity(100);
        droneDto.setModel(DroneModel.Lightweight);
        droneDto.setState(DroneState.LOADED);

        assertFalse(uut.validate(droneDto).isValid());
    }

    @Test
    void validationFailedWrongWeight() {
        var droneDto = new DroneDto();

        droneDto.setSerialNumber("a".repeat(200));
        droneDto.setWeight(-434);
        droneDto.setBatteryCapacity(100);
        droneDto.setModel(DroneModel.Lightweight);
        droneDto.setState(DroneState.LOADED);

        assertFalse(uut.validate(droneDto).isValid());
    }

    @Test
    void validationFailedWrongBatteryCapacity() {
        var droneDto = new DroneDto();

        droneDto.setSerialNumber("a".repeat(200));
        droneDto.setWeight(-434);
        droneDto.setBatteryCapacity(101);
        droneDto.setModel(DroneModel.Lightweight);
        droneDto.setState(DroneState.LOADED);

        assertFalse(uut.validate(droneDto).isValid());
    }
}
