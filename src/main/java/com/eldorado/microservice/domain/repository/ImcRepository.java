package com.eldorado.microservice.domain.repository;

import com.eldorado.microservice.domain.model.ImcEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface ImcRepository extends MongoRepository<ImcEntity, UUID> {
    List<ImcEntity> findAllByClientId(UUID clientId);
}
