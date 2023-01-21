package com.eldorado.microservice.domain.repository;

import com.eldorado.microservice.domain.model.ImcEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ImcRepository extends MongoRepository<ImcEntity, UUID> {

    List<ImcEntity> findByClientId(UUID cliendId);
    void deleteAllByClientId(UUID clientId);
    void deleteByMeasurementId(UUID measurementId);
}
