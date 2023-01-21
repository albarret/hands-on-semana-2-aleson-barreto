package com.eldorado.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImcBaseDto {
    private UUID id;
    private float bodyMass;
    private float height;
    private String classification;
    private String obesityLevel;
    private UUID client;
    private LocalDateTime mesurementDate;
}
