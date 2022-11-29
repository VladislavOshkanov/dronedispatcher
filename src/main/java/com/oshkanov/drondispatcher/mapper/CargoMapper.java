package com.oshkanov.drondispatcher.mapper;

import com.oshkanov.drondispatcher.dao.CargoEntity;
import com.oshkanov.drondispatcher.dto.CargoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    CargoDto toDto(CargoEntity entity);

    @Mapping(target = "fileName", ignore = true)
    CargoEntity toEntity(CargoDto dto);
}
