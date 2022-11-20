package com.oshkanov.drondispatcher.mapper;

import java.util.UUID;

import com.oshkanov.drondispatcher.dao.CargoEntity;
import com.oshkanov.drondispatcher.dto.CargoDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CargoMapperTest {

    private static final UUID CARGO_UUIT = UUID.randomUUID();

    private static final String NAME = "name";

    private static final Integer WEIGHT = 100;

    private static final String CODE = "CODE";

    private static final String FILE_NAME = "fileName";

    // uut - unit under testing
    CargoMapper uut = Mappers.getMapper(CargoMapper.class);


    @Test
    void toDto() {
        CargoEntity cargoEntity = new CargoEntity();

        cargoEntity.setId(CARGO_UUIT);
        cargoEntity.setName(NAME);
        cargoEntity.setWeight(WEIGHT);
        cargoEntity.setCode(CODE);
        cargoEntity.setFileName(FILE_NAME);

        CargoDto cargoDto = uut.toDto(cargoEntity);

        assertEquals(CARGO_UUIT, cargoDto.getId());
        assertEquals(NAME, cargoDto.getName());
        assertEquals(WEIGHT, cargoDto.getWeight());
        assertEquals(CODE, cargoDto.getCode());
        assertEquals(FILE_NAME, cargoDto.getFileName());

    }

    @Test
    void toEntity() {
        CargoDto cargoDto = new CargoDto();

        cargoDto.setId(CARGO_UUIT);
        cargoDto.setName(NAME);
        cargoDto.setWeight(WEIGHT);
        cargoDto.setCode(CODE);
        cargoDto.setFileName(FILE_NAME);

        CargoEntity cargoEntity = uut.toEntity(cargoDto);

        assertEquals(CARGO_UUIT, cargoEntity.getId());
        assertEquals(NAME, cargoEntity.getName());
        assertEquals(WEIGHT, cargoEntity.getWeight());
        assertEquals(CODE, cargoEntity.getCode());
        assertNull(cargoEntity.getFileName());
    }
}
