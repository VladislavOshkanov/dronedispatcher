package com.oshkanov.drondispatcher.repository;

import java.util.Optional;
import java.util.UUID;

import com.oshkanov.drondispatcher.dao.DroneEntity;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<DroneEntity, UUID> {

    Optional<DroneEntity> findFirstBySerialNumber(String serialNumber);
}
