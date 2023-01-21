package com.eldorado.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientDto {

    private UUID id;
    private String name;
    private String gender;
    private String identificationNumber;
    private String birthDate;
    private Integer gymMonthFrequency;
    private String phoneNumber;
    private String address;
    private List<MeasurementDto> measurements;
}
