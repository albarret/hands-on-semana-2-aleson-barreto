package com.eldorado.microservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("measurement")
public class MesurementEntity {

    @Id
    private UUID id;
    private Double weight;
    private Double height;
    private LocalDateTime measurementDate;
    private UUID clientId;
}
