package com.oshkanov.drondispatcher.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class DroneDto {
    private UUID id;

    private String serialNumber;

    private String model;

    private Integer weight;

    private Integer batteryCapacity;

    private String state;
}
