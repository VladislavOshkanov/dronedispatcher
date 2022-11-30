package com.oshkanov.drondispatcher.dto;

import java.util.List;
import java.util.UUID;

import com.oshkanov.drondispatcher.model.DroneModel;
import com.oshkanov.drondispatcher.model.DroneState;
import lombok.Data;

@Data
public class DroneDto {
    private UUID id;

    private String serialNumber;

    private DroneModel model;

    private Integer weight;

    private Integer batteryCapacity;

    private DroneState state;

    private List<CargoDto> cargo;
}
