package com.oshkanov.drondispatcher.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    private Integer weight;

    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToMany
    List<CargoEntity> cargo = new ArrayList<>();
}
