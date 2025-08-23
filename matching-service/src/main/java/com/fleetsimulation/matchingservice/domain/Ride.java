package com.fleetsimulation.matchingservice.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "rides")
public class Ride {
    public enum Status {
        REQUESTED, ASSIGNED, PICKUP, IN_PROGRESS, COMPLETED, CANCELED
    }

    @Id
    @Column(length = 40)
    private String rideId;

    @Column(nullable = false, length = 40)
    private String riderId;

    @Column(nullable = false) private double pickupLat;
    @Column(nullable = false) private double pickupLng;
    @Column(nullable = false) private double dropOffLat;
    @Column(nullable = false) private double dropOffLng;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status=Status.REQUESTED;

    @Column(length = 40)
    private String vehicleId;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    @PreUpdate
    void touch() {
        this.updatedAt = Instant.now();
    }

    public Ride() {

    }

    public Ride(String rideId, String riderId, double pickupLat, double pickupLng, double dropOffLat, double dropOffLng, Status status, String vehicleId, Instant createdAt, Instant updatedAt) {
        this.rideId = rideId;
        this.riderId = riderId;
        this.pickupLat = pickupLat;
        this.pickupLng = pickupLng;
        this.dropOffLat = dropOffLat;
        this.dropOffLng = dropOffLng;
        this.status = status;
        this.vehicleId = vehicleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public double getPickupLat() {
        return pickupLat;
    }

    public void setPickupLat(double pickupLat) {
        this.pickupLat = pickupLat;
    }

    public double getPickupLng() {
        return pickupLng;
    }

    public void setPickupLng(double pickupLng) {
        this.pickupLng = pickupLng;
    }

    public double getDropOffLat() {
        return dropOffLat;
    }

    public void setDropOffLat(double dropOffLat) {
        this.dropOffLat = dropOffLat;
    }

    public double getDropOffLng() {
        return dropOffLng;
    }

    public void setDropOffLng(double dropOffLng) {
        this.dropOffLng = dropOffLng;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
