package com.eldorado.microservice.domain.repository;

import com.eldorado.microservice.domain.model.MesurementEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MeasurementRepository extends MongoRepository<MesurementEntity, UUID> {
    List<MesurementEntity> findAllByClientId(UUID clientId);
    void deleteAllByClientId(UUID clientId);
}
