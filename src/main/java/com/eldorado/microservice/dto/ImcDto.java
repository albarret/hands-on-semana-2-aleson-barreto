package com.eldorado.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImcDto {

    private UUID id;
    private UUID mesurementId;
    private UUID clientId;
    private Double imc;
    private String classification;
    private String obesityLevel;
}
