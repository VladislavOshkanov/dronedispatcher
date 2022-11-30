package com.oshkanov.drondispatcher.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignCargoDto {
    UUID droneId;
    UUID cargoId;
}
