package com.eldorado.microservice.domain.repository;

import com.eldorado.microservice.domain.model.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ClientRepository extends MongoRepository<ClientEntity, UUID> {
}
