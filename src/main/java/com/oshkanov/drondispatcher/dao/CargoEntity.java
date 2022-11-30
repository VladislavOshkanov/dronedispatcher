package com.oshkanov.drondispatcher.dao;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cargo")
public class CargoEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Integer weight;

    private String code;

    private String fileName;

    @ManyToOne
    private DroneEntity dronAssigned;
}
