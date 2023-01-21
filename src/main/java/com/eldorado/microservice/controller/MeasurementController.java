package com.eldorado.microservice.controller;

import com.eldorado.microservice.dto.MeasurementDto;
import com.eldorado.microservice.service.ClientService;
import com.eldorado.microservice.service.ImcService;
import com.eldorado.microservice.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/imc-menagement")
@Slf4j
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ClientService clientService;
    private final ImcService imcService;

    @DeleteMapping("/{measurementId}")
    public void deleteImc(@PathVariable String measurementId) {
        measurementService.deleteMeasurementsById(UUID.fromString(measurementId));
    }

    @PostMapping("/{clientId}")
    public ResponseEntity<MeasurementDto> saveMeasurement(
            @PathVariable String clientId,
            @RequestBody MeasurementDto measurement) throws NoSuchElementException{

        var client = clientService.fetchClientById(UUID.fromString(clientId));
        measurement.setClient(client.getId());

        var response = measurementService.saveMeasurement(measurement);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<MeasurementDto>> getMeasurementsForClient(@PathVariable String clientId) throws NoSuchElementException {
        return ResponseEntity.ok(measurementService.fetchAllMeasurementForClient(UUID.fromString(clientId)));
    }

    @GetMapping("/{clientId}/report")
    public ResponseEntity<String> getImcReportForClient(@PathVariable String clientId) {
        var measurements = measurementService.fetchAllMeasurementForClient(UUID.fromString(clientId));
        return ResponseEntity.ok(imcService.createImcReport(measurements));
    }


}
