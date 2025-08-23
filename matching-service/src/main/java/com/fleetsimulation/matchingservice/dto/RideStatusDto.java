package com.fleetsimulation.matchingservice.dto;

public class RideStatusDto {
    public String rideId;
    public String status;
    public String vehicleId;

    public RideStatusDto() {
    }

    public RideStatusDto(String rideId, String status, String vehicleId) {
        this.rideId = rideId;
        this.status = status;
        this.vehicleId = vehicleId;
    }
}
