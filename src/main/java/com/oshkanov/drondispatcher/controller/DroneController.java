package com.oshkanov.drondispatcher.controller;

import com.oshkanov.drondispatcher.dto.AssignCargoDto;
import com.oshkanov.drondispatcher.dto.CargoDto;
import com.oshkanov.drondispatcher.service.DroneService;
import com.oshkanov.drondispatcher.dto.DroneDto;
import com.oshkanov.drondispatcher.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drones")
public class DroneController {

    private final DroneService droneService;

    @GetMapping
    ResponseDto<Iterable<DroneDto>> getAllDrones() {
        return droneService.getAllDrones();
    }

    @GetMapping("/battery-capacity")
    ResponseDto<Integer> getBatteryCapacity(String droneSerialNumber) {
        return droneService.getBatteryCapacity(droneSerialNumber);
    }

    @PostMapping
    ResponseDto<DroneDto> registerDrone(@RequestBody DroneDto droneDto) {
        return droneService.registerDrone(droneDto);
    }

    @PostMapping("/assign-cargo")
    ResponseDto<DroneDto> assignCargo(@RequestBody AssignCargoDto assignCargoDto) {
        return droneService.assignCargo(assignCargoDto);
    }
}
