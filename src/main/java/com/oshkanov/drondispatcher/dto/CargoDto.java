package com.oshkanov.drondispatcher.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CargoDto {
    private UUID id;

    private String name;

    private Integer weight;

    private String code;

    private String fileName;
}
