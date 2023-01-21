package com.eldorado.microservice.service;

import com.eldorado.microservice.domain.model.MesurementEntity;
import com.eldorado.microservice.domain.repository.MeasurementRepository;
import com.eldorado.microservice.dto.ImcDto;
import com.eldorado.microservice.dto.MeasurementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final ImcService imcService;

    public MeasurementDto saveMeasurement(MeasurementDto measurementDto) throws NoSuchElementException {

        var measurementEntity = MesurementEntity.builder()
                .id(measurementDto.getId() != null ? measurementDto.getId() : UUID.randomUUID())
                .weight(measurementDto.getWeight())
                .height(measurementDto.getHeight())
                .measurementDate(LocalDateTime.now())
                .clientId(measurementDto.getClient()).build();

        var imcDto = calcularImc(measurementDto.getWeight(), measurementDto.getHeight());
        imcDto.setMesurementId(measurementEntity.getId());
        imcDto.setClientId(measurementEntity.getClientId());

        measurementRepository.save(measurementEntity);

        measurementDto.setImc(imcService.saveImc(imcDto));

        log.info("Measurement SAVED SUCESSEFULLY {}", measurementEntity);
        measurementDto.setId(measurementEntity.getId());
        measurementDto.setMesurementDate(measurementEntity.getMeasurementDate());

        return measurementDto;
    }

    public List<MeasurementDto> fetchAllMeasurementForClient(UUID clientId) {
        var clientMeasurements = measurementRepository.findAllByClientId(clientId);
        var imcs = imcService.fetchAllImcsForClient(clientId);

        var measurementDtos = clientMeasurements.stream().map(cm -> {
            var imcDto = imcs.stream().filter(imc -> imc.getMesurementId().equals(cm.getId()))
                    .findFirst().orElse(calcularImc(cm.getWeight(), cm.getHeight()));
            return MeasurementDto.builder()
                    .client(cm.getClientId())
                    .weight(cm.getWeight())
                    .height(cm.getHeight())
                    .mesurementDate(cm.getMeasurementDate())
                    .imc(imcDto)
                    .id(cm.getId()).build();
        }).toList();

        log.info("IMCS FETCHED SUCESSEFULLY {}", measurementDtos);

        return measurementDtos;
    }

    public void deleteMeasurementsByClientId(UUID clientId) {

        measurementRepository.deleteAllByClientId(clientId);
    }

    private ImcDto calcularImc(double weight, double height) {
        var imc = weight / (height * height);
        String classification;
        String obesityLevel;

        if (imc < 18.5) {
            classification = "Magresa";
        } else if (imc < 25) {
            classification = "Normal";
        } else if (imc < 30) {
            classification = "Sobrepeso";
        } else if (imc < 40) {
            classification = "Obesidade";
        } else {
            classification = "Obesidade Grave";
        }

        obesityLevel = switch (classification) {
            case "Magresa", "Normal" -> "0";
            case "Sobrepeso" -> "I";
            case "Obesidade" -> "II";
            default -> "III";
        };

        return ImcDto.builder()
                .classification(classification)
                .imc(imc)
                .obesityLevel(obesityLevel).build();
    }

    public void deleteMeasurementsById(UUID measurementId) {
        imcService.deleteImcsByMeasurementId(measurementId);
        measurementRepository.deleteById(measurementId);
    }
}
