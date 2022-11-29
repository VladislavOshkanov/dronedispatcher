package com.oshkanov.drondispatcher.mapper;

import com.oshkanov.drondispatcher.dao.DroneEntity;
import com.oshkanov.drondispatcher.dto.DroneDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DroneMapper {
    DroneDto toDto(DroneEntity entity);

    Iterable<DroneDto> toDtoIterable(Iterable<DroneEntity> droneEntities);

    DroneEntity toEntity(DroneDto dto);
}
