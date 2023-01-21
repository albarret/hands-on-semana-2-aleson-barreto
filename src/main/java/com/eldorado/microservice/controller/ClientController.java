package com.eldorado.microservice.controller;

import com.eldorado.microservice.dto.ClientDto;
import com.eldorado.microservice.dto.MeasurementDto;
import com.eldorado.microservice.service.ClientService;
import com.eldorado.microservice.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final MeasurementService measurementService;

    @PostMapping
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientDto clientDto) {
        var response = clientService.saveClient(clientDto);
        log.info("Cliente {} ", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable String clientId) {
        var response = clientService.fetchClientById(UUID.fromString(clientId));
        log.info("Cliente {} ", response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{clientId}")
    public void updateClient(@PathVariable String clientId, @RequestBody ClientDto client) {
        log.warn("UUID: {}, ClientDTO: {}", clientId, client);
        client.setId(UUID.fromString(clientId));
        clientService.saveClient(client);
    }

    @DeleteMapping("/{clientId}")
    public void deleteImc(@PathVariable String clientId) {
        clientService.deleteClient(UUID.fromString(clientId));
    }

    @GetMapping("/{clientId}/imc")
    public ResponseEntity<List<MeasurementDto>> getImcs(@PathVariable String clientId) {
        var response = measurementService.fetchAllMeasurementForClient(UUID.fromString(clientId));
        return ResponseEntity.ok(response);
    }
}
