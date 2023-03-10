package com.eldorado.microservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document("imc")
public class ImcEntity {
    @Id
    private UUID id;
    private UUID measurementId;
    private UUID clientId;
    private double imc;
    private String classification;
    private String obesityLevel;
}
