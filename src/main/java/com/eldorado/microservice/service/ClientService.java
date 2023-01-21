package com.eldorado.microservice.service;

import com.eldorado.microservice.domain.model.ClientEntity;
import com.eldorado.microservice.domain.repository.ClientRepository;
import com.eldorado.microservice.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    private final MeasurementService measurementService;

    public ClientDto saveClient(ClientDto client) {
        var clientEntity = ClientEntity.builder()
                .id(client.getId() != null ? client.getId() : UUID.randomUUID())
                .name(client.getName())
                .gender(client.getGender())
                .identificationNumber(client.getIdentificationNumber())
                .birthDate(client.getBirthDate())
                .gymMonthFrequency(client.getGymMonthFrequency())
                .phoneNumber(client.getPhoneNumber())
                .address(client.getAddress())
                .build();

        clientEntity = clientRepository.save(clientEntity);
        client.setId(clientEntity.getId());

        return client;
    }

    public void deleteClient(UUID clientId) {
        var clientEntity = clientRepository.findById(clientId).orElseThrow();
        clientRepository.delete(clientEntity);
    }

    public ClientDto fetchClientById(UUID clientId) throws NoSuchElementException {
        var clientEntity = clientRepository.findById(clientId).orElseThrow();
        var clientMeasurements = measurementService.fetchAllMeasurementForClient(clientEntity.getId());
        return ClientDto.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .gender(clientEntity.getGender())
                .identificationNumber(clientEntity.getIdentificationNumber())
                .birthDate(clientEntity.getBirthDate())
                .gymMonthFrequency(clientEntity.getGymMonthFrequency())
                .phoneNumber(clientEntity.getPhoneNumber())
                .address(clientEntity.getAddress())
                .measurements(clientMeasurements)
                .build();
    }
}
