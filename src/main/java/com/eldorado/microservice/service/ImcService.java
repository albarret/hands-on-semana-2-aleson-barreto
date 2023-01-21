package com.eldorado.microservice.service;

import com.eldorado.microservice.domain.model.ImcEntity;
import com.eldorado.microservice.domain.repository.ImcRepository;
import com.eldorado.microservice.dto.ImcBaseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImcService {

    private final ImcRepository imcRepository;

    public ImcBaseDto saveImc(ImcBaseDto imcBaseDto) {

        var imcEntity = ImcEntity.builder()
                .id(UUID.randomUUID())
                .bodyMass(imcBaseDto.getBodyMass())
                .height(imcBaseDto.getHeight())
                .classification(imcBaseDto.getClassification())
                .obesityLevel(imcBaseDto.getObesityLevel())
                .offsetDateTime(LocalDateTime.now())
                .clientId(imcBaseDto.getClient()).build();


        var imcEntitySave = imcRepository.save(imcEntity);

        // upadte client document adding new bmi to the list of bmis? or does it surfice to aggregate this list if needed?

        log.info("IMC SAVED WITH SUCESSEFUL {}", imcEntitySave);
        imcBaseDto.setId(imcEntitySave.getId());

        return imcBaseDto;
    }

    public List<ImcBaseDto> fetchAllImcForClient(UUID clientId) {
        var clientImcs = imcRepository.findAllByClientId(clientId);

        var imcDtos = clientImcs.stream().map(ci ->
           ImcBaseDto.builder()
                   .client(ci.getClientId())
                   .bodyMass(ci.getBodyMass())
                   .height(ci.getHeight())
                   .mesurementDate(ci.getOffsetDateTime())
                   .classification(ci.getClassification())
                   .id(ci.getId())
                   .obesityLevel(ci.getObesityLevel()).build()
        ).collect(Collectors.toList());

        log.info("IMCS FETCHED SUCESSEFULLY {}", imcDtos);

        return imcDtos;
    }

//    public void updateClientBodyMassAndHeight(UUID clientId, float bodyMass, float height) {
//        var imc = imcRepository.findByClientId()
//    }


}
