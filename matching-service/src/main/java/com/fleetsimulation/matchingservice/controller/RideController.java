package com.fleetsimulation.matchingservice.controller;

import com.fleetsimulation.matchingservice.dto.RideRequest;
import com.fleetsimulation.matchingservice.dto.RideResponse;
import com.fleetsimulation.matchingservice.dto.RideStatusDto;
import com.fleetsimulation.matchingservice.service.RiderService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RiderService riderService;
    public RideController(RiderService riderService) {
        this.riderService = riderService;
    }

    @PostMapping
    public ResponseEntity<RideResponse> create(@Valid @RequestBody RideRequest rideRequest) {
        RideResponse res = riderService.createRide(rideRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<RideStatusDto> get(@PathVariable String rideId) {
        RideStatusDto dto = riderService.getStatus(rideId);
        return (dto==null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }
}
