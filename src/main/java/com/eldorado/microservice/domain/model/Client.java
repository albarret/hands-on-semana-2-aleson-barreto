package com.eldorado.microservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("client")
public class Client {

    @Id
    private UUID id;
    private String name;
    private String gender;
    private String identificationNumber;
    private String bithDate;
    private Integer gymMonthFrequency;
    private String phoneNumber;
    private String address;
}
