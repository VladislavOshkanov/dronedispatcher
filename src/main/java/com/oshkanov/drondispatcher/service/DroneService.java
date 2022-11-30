package com.oshkanov.drondispatcher.service;

import java.util.List;
import java.util.Optional;

import com.oshkanov.drondispatcher.dao.CargoEntity;
import com.oshkanov.drondispatcher.dao.DroneEntity;
import com.oshkanov.drondispatcher.dto.AssignCargoDto;
import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.dto.ResponseDto;
import com.oshkanov.drondispatcher.mapper.DroneMapper;
import com.oshkanov.drondispatcher.model.RequestStatus;
import com.oshkanov.drondispatcher.model.ValidationResult;
import com.oshkanov.drondispatcher.repository.CargoRepository;
import com.oshkanov.drondispatcher.repository.DroneRepository;
import com.oshkanov.drondispatcher.validator.drone.DroneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DroneService {
    public static final int MINIMAL_BATTERY_PERCENT = 25;
    private final DroneRepository droneRepository;

    private final CargoRepository cargoRepository;

    private final DroneMapper droneMapper;

    private final DroneValidator droneValidator;

    public ResponseDto<Iterable<DroneDto>> getAllDrones() {
        return new ResponseDto<>(RequestStatus.SUCCESS,
                droneMapper.toDtoIterable(droneRepository.findAll()));
    }

    public ResponseDto<Integer> getBatteryCapacity(String serialNumber) {
        Optional<DroneEntity> droneEntityOptional =
                droneRepository.findFirstBySerialNumber(serialNumber);

        if (droneEntityOptional.isPresent()) {
            return new ResponseDto<>(RequestStatus.SUCCESS, droneEntityOptional.get().getBatteryCapacity());
        } else {
            return new ResponseDto<>(RequestStatus.FAILED, List.of("Drone with this serial number not found"));
        }

    }

    public ResponseDto<DroneDto> registerDrone(DroneDto droneDto) {
        ValidationResult validationResult = droneValidator.validate(droneDto);

        if (validationResult.isValid()) {
            DroneEntity droneEntity = droneRepository.save(droneMapper.toEntity(droneDto));
            return new ResponseDto<>(RequestStatus.SUCCESS, droneMapper.toDto(droneEntity));
        } else {
            return new ResponseDto<>(RequestStatus.FAILED, validationResult.getErrorMessages());
        }
    }

    public ResponseDto<DroneDto> assignCargo(AssignCargoDto assignCargoDto) {

        Optional<DroneEntity> droneFromDb = droneRepository.findById(assignCargoDto.getDroneId());
        Optional<CargoEntity> cargoFromDb = cargoRepository.findById(assignCargoDto.getCargoId());

        if (droneFromDb.isEmpty()) {
            return new ResponseDto<>(RequestStatus.FAILED,
                    List.of(String.format("Drone with id %s not exists", assignCargoDto.getDroneId())));
        }
        if (cargoFromDb.isEmpty()) {
            return new ResponseDto<>(RequestStatus.FAILED,
                    List.of(String.format("Cargo with id %s not exists", assignCargoDto.getCargoId())));
        }

        var drone = droneFromDb.get();
        var cargo = cargoFromDb.get();

        var droneLoadWeight = drone.getCargo().stream().map(CargoEntity::getWeight)
                        .reduce(0, Integer::sum);

        if (droneLoadWeight + cargo.getWeight() > drone.getWeight()) {
            return new ResponseDto<>(RequestStatus.FAILED,
                    List.of("Max weight exceeded."));
        }

        if (drone.getBatteryCapacity() < MINIMAL_BATTERY_PERCENT) {
            return new ResponseDto<>(RequestStatus.FAILED,
                    List.of(String.format("Drone battery capacity is below %s", MINIMAL_BATTERY_PERCENT)));
        }

        drone.getCargo().add(cargo);

        drone = droneRepository.save(drone);

        return new ResponseDto<>(RequestStatus.SUCCESS, droneMapper.toDto(drone));
    }

}
