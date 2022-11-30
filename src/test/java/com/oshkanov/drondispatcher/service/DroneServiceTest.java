package com.oshkanov.drondispatcher.service;

import java.util.Optional;
import java.util.UUID;

import com.oshkanov.drondispatcher.dao.CargoEntity;
import com.oshkanov.drondispatcher.dao.DroneEntity;
import com.oshkanov.drondispatcher.dto.AssignCargoDto;
import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.dto.ResponseDto;
import com.oshkanov.drondispatcher.mapper.DroneMapper;
import com.oshkanov.drondispatcher.model.ValidationResult;
import com.oshkanov.drondispatcher.repository.CargoRepository;
import com.oshkanov.drondispatcher.repository.DroneRepository;
import com.oshkanov.drondispatcher.validator.drone.DroneValidator;
import liquibase.pro.packaged.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.client.ExpectedCount.times;

class DroneServiceTest {

    private static final UUID DRONE_ID = UUID.randomUUID();

    private static final UUID CARGO_ID = UUID.randomUUID();

    private AutoCloseable autoCloseable;
    @Mock
    private DroneRepository droneRepository;

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private DroneMapper droneMapper;

    @Mock
    private DroneValidator droneValidator;

    @Captor
    ArgumentCaptor<DroneEntity> droneEntityArgumentCaptor;

    @InjectMocks
    DroneService uut;

    @BeforeEach
    void init() {
        autoCloseable = openMocks(this);
    }

    @AfterEach
    void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getBatteryCapacity() {
        var droneEntity = new DroneEntity();
        droneEntity.setBatteryCapacity(44);

        when(droneRepository.findFirstBySerialNumber("123"))
                .thenReturn(Optional.of(droneEntity));

        assertEquals(44, uut.getBatteryCapacity("123").getResponse());

    }

    @Test
    void registerDrone() {
        var droneDto = new DroneDto();
        var droneEntity = new DroneEntity();

        when(droneValidator.validate(any(DroneDto.class)))
                .thenReturn(new ValidationResult(true, null));
        when(droneMapper.toEntity(any(DroneDto.class))).thenReturn(droneEntity);
        when(droneMapper.toDto(any(DroneEntity.class))).thenReturn(droneDto);
        when(droneRepository.save(droneEntity)).thenReturn(droneEntity);

        ResponseDto<DroneDto> response = uut.registerDrone(droneDto);

        verify(droneRepository).save(droneEntity);

        assertEquals(droneDto, response.getResponse());
    }

    @Test
    void assignCargo() {
        var assignCargoDto = new AssignCargoDto(DRONE_ID, CARGO_ID);

        var droneEntity = new DroneEntity();
        droneEntity.setWeight(400);
        droneEntity.setBatteryCapacity(50);

        var cargoEntity = new CargoEntity();
        cargoEntity.setWeight(40);

        when(droneRepository.findById(DRONE_ID)).thenReturn(Optional.of(droneEntity));
        when(cargoRepository.findById(CARGO_ID)).thenReturn(Optional.of(cargoEntity));

        uut.assignCargo(assignCargoDto);

        verify(droneRepository).save(droneEntityArgumentCaptor.capture());

        DroneEntity value = droneEntityArgumentCaptor.getValue();

        assertEquals(1, value.getCargo().size());
    }
}
