package com.eldorado.microservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
public class MeasurementDto {
    private UUID id;
    private Double weight;
    private Double height;
    private UUID client;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime mesurementDate;
    private ImcDto imc;

    public String toString() {
        return "Data de ferição: %s, Peso: %s, Altura: %s %n".formatted(
                mesurementDate.toLocalDate().toString(),
                weight.toString(),
                height.toString());
    }
}
