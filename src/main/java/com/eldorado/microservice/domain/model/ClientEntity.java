package com.eldorado.microservice.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "client")
public class ClientEntity {

    @Id
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String gender;
    private String identificationNumber;
    @NonNull
    private String birthDate;
    @NonNull
    private Integer gymMonthFrequency;
    private String phoneNumber;
    @NonNull
    private String address;
}
