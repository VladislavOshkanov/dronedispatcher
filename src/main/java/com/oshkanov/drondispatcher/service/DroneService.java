package com.oshkanov.drondispatcher;

import com.oshkanov.drondispatcher.dao.DroneEntity;
import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.dto.ResponseDto;
import com.oshkanov.drondispatcher.mapper.DroneMapper;
import com.oshkanov.drondispatcher.model.RequestStatus;
import com.oshkanov.drondispatcher.model.ValidationResult;
import com.oshkanov.drondispatcher.repository.DroneRepository;
import com.oshkanov.drondispatcher.validator.drone.DroneValidator;
import liquibase.pro.packaged.P;
import liquibase.pro.packaged.V;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;

    private final DroneMapper droneMapper;

    private final DroneValidator droneValidator;

    public ResponseDto<DroneDto> registerDrone(DroneDto droneDto) {
        ValidationResult validationResult = droneValidator.validate(droneDto);

        if (validationResult.isValid()) {
            DroneEntity droneEntity = droneRepository.save(droneMapper.toEntity(droneDto));
            return new ResponseDto<>(RequestStatus.SUCCESS, droneMapper.toDto(droneEntity));
        } else {
            return new ResponseDto<>(RequestStatus.FAILED, validationResult.getErrorMessages());
        }
    }

    public ResponseDto

}
