package com.oshkanov.drondispatcher.mapper;

import java.util.UUID;

import com.oshkanov.drondispatcher.dao.DroneEntity;
import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.model.DroneModel;
import com.oshkanov.drondispatcher.model.DroneState;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DroneMapperTest {

    private static final UUID DRONE_UUID = UUID.randomUUID();
    private static final String SERIAL_NUMBER = "12345";
    private static final Integer WEIGHT = 100;
    private static final Integer BATTERY_CAPACITY = 200;
    private static final DroneState DRONE_STATE = DroneState.DELIVERED;
    private static final String DRONE_STATE_STRING = "DELIVERED";
    private static final DroneModel DRONE_MODEL = DroneModel.Cruiserweight;
    private static final String DRONE_MODEL_STRING = "Cruiserweight";

    DroneMapper uut = Mappers.getMapper(DroneMapper.class);

    @Test
    void toDto() {
        DroneEntity droneEntity = new DroneEntity();
        droneEntity.setId(DRONE_UUID);
        droneEntity.setSerialNumber(SERIAL_NUMBER);
        droneEntity.setBatteryCapacity(BATTERY_CAPACITY);
        droneEntity.setWeight(WEIGHT);
        droneEntity.setState(DRONE_STATE);
        droneEntity.setModel(DRONE_MODEL);


        DroneDto droneDto = uut.toDto(droneEntity);

        assertEquals(DRONE_UUID, droneDto.getId());
        assertEquals(SERIAL_NUMBER, droneDto.getSerialNumber());
        assertEquals(BATTERY_CAPACITY, droneDto.getBatteryCapacity());
        assertEquals(WEIGHT, droneDto.getWeight());
        assertEquals(DRONE_STATE_STRING, droneDto.getState());
        assertEquals(DRONE_MODEL_STRING, droneDto.getModel());

    }

    @Test
    void toEntity() {
        DroneDto droneDto = new DroneDto();

        droneDto.setId(DRONE_UUID);
        droneDto.setSerialNumber(SERIAL_NUMBER);
        droneDto.setBatteryCapacity(BATTERY_CAPACITY);
        droneDto.setWeight(WEIGHT);
        droneDto.setState(DRONE_STATE_STRING);
        droneDto.setModel(DRONE_MODEL_STRING);

        DroneEntity droneEntity = uut.toEntity(droneDto);

        assertEquals(DRONE_UUID, droneEntity.getId());
        assertEquals(SERIAL_NUMBER, droneEntity.getSerialNumber());
        assertEquals(BATTERY_CAPACITY, droneEntity.getBatteryCapacity());
        assertEquals(WEIGHT, droneEntity.getWeight());
        assertEquals(DRONE_STATE, droneEntity.getState());
        assertEquals(DRONE_MODEL, droneEntity.getModel());
    }
}
