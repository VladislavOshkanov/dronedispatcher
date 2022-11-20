package com.oshkanov.drondispatcher.dao;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.oshkanov.drondispatcher.model.DroneModel;
import com.oshkanov.drondispatcher.model.DroneState;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "drones")
public class DroneEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String serialNumber;

    private DroneModel model;

    private Integer weight;

    private Integer batteryCapacity;

    private DroneState state;
}
