package com.eldorado.microservice.service;

import com.eldorado.microservice.domain.model.ImcEntity;
import com.eldorado.microservice.domain.repository.ImcRepository;
import com.eldorado.microservice.dto.ImcDto;
import com.eldorado.microservice.dto.MeasurementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImcService {

    private final ImcRepository imcRepository;

    public ImcDto saveImc(ImcDto imcDto) {
        var imcEntity = ImcEntity.builder()
            .imc(imcDto.getImc())
            .classification(imcDto.getClassification())
            .obesityLevel(imcDto.getObesityLevel())
            .measurementId(imcDto.getMesurementId())
            .id(imcDto.getId() != null ? imcDto.getId() : UUID.randomUUID())
            .clientId(imcDto.getClientId()).build();

        var imc = imcRepository.save(imcEntity);
        imcDto.setId(imc.getId());
        return imcDto;
    }

    public List<ImcDto> fetchAllImcsForClient(UUID clientId) {

        return imcRepository.findByClientId(clientId).stream().map(imc ->
                ImcDto.builder()
                    .imc(imc.getImc())
                    .classification(imc.getClassification())
                    .obesityLevel(imc.getObesityLevel())
                    .mesurementId(imc.getMeasurementId())
                    .id(imc.getId())
                    .clientId(imc.getClientId()).build()
            ).toList();

    }

    public void deleteImcsByClientId(UUID clientId) {
        imcRepository.deleteAllByClientId(clientId);
    }

    public String createImcReport(List<MeasurementDto> measurements) {
        var report = new StringBuilder();

        measurements.sort(Comparator.comparing(MeasurementDto::getMesurementDate));
        String previousClassification = measurements.get(0).getImc().getClassification();
        LocalDateTime previousDateTime = measurements.get(0).getMesurementDate();

        for(MeasurementDto measurement: measurements) {
            report.append(measurement.toString());
        }

        report.append("\n");

        for(MeasurementDto measurement: measurements) {
            if(!measurement.getImc().getClassification().equalsIgnoreCase(previousClassification)) {
                report.append("Entre %s e %s, a classificação variou de %s para %s %n".formatted(
                        previousDateTime.toLocalDate(),
                        measurement.getMesurementDate().toLocalDate(),
                        previousClassification,
                        measurement.getImc().getClassification()));
                previousDateTime = measurement.getMesurementDate();
                previousClassification = measurement.getImc().getClassification();
            }
        }

        return report.toString();
    }

    public void deleteImcsByMeasurementId(UUID measurementId) {
        imcRepository.deleteByMeasurementId(measurementId);
    }
}
