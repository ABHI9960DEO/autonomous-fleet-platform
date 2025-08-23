package com.fleetsimulation.matchingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RideRequest {
    @NotBlank public String riderId;
    @NotNull

    public Double pickUpLat;
    @NotNull public Double pickUpLon;
    @NotNull public Double dropoffLat;
    @NotNull public Double dropoffLon;

    public RideRequest() {

    }

    public RideRequest(String riderId, Double pickUpLat, Double pickUpLon, Double dropoffLat, Double dropoffLon) {
        this.riderId = riderId;
        this.pickUpLat = pickUpLat;
        this.pickUpLon = pickUpLon;
        this.dropoffLat = dropoffLat;
        this.dropoffLon = dropoffLon;
    }

    public @NotBlank String getRiderId() {
        return riderId;
    }

    public void setRiderId(@NotBlank String riderId) {
        this.riderId = riderId;
    }

    public @NotNull Double getPickUpLat() {
        return pickUpLat;
    }

    public void setPickUpLat(@NotNull Double pickUpLat) {
        this.pickUpLat = pickUpLat;
    }

    public @NotNull Double getPickUpLon() {
        return pickUpLon;
    }

    public void setPickUpLon(@NotNull Double pickUpLon) {
        this.pickUpLon = pickUpLon;
    }

    public @NotNull Double getDropoffLat() {
        return dropoffLat;
    }

    public void setDropoffLat(@NotNull Double dropoffLat) {
        this.dropoffLat = dropoffLat;
    }

    public @NotNull Double getDropoffLon() {
        return dropoffLon;
    }

    public void setDropoffLon(@NotNull Double dropoffLon) {
        this.dropoffLon = dropoffLon;
    }
}
