package com.fleetsimulation.matchingservice.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    public enum Status{
        AVAILABLE, ASSIGNED, OFFLINE
    }

    @Id
    @Column(length = 40)
    private String vehicleId;

    @Column(nullable = false) private double lastLat;
    @Column(nullable = false) private double lastLon;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.AVAILABLE;

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    @PreUpdate
    public void touch() {
        this.updatedAt = Instant.now();
    }

    public Vehicle() {

    }

    public Vehicle(String vehicleId, double lastLat, double lastLon, Status status, Instant updatedAt) {
        this.vehicleId = vehicleId;
        this.lastLat = lastLat;
        this.lastLon = lastLon;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getLastLat() {
        return lastLat;
    }

    public void setLastLat(double lastLat) {
        this.lastLat = lastLat;
    }

    public double getLastLon() {
        return lastLon;
    }

    public void setLastLon(double lastLon) {
        this.lastLon = lastLon;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
