package com.oshkanov.drondispatcher.repository;

import java.util.UUID;

import com.oshkanov.drondispatcher.dao.CargoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CargoRepository extends CrudRepository<CargoEntity, UUID> {
}
