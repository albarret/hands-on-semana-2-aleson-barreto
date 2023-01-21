package com.eldorado.microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientDto {

    private String name;
    private Integer gymMonthFrequency;
    private String gender;
    private List<ImcBaseDto> imcs;
}
