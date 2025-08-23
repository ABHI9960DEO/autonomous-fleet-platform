package com.fleetsimulation.matchingservice.dto;

import jakarta.validation.constraints.NotBlank;

public class RideResponse {
    public String rideId;
    public String status;
    public String vehicleId;

    public RideResponse() {
    }

    public RideResponse(String rideId, String status, String vehicleId) {
        this.rideId = rideId;
        this.status = status;
        this.vehicleId = vehicleId;
    }
}
