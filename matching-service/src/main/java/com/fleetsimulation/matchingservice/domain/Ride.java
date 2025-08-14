package com.fleetsimulation.matchingservice.domain;

import jakarta.persistence.*;

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

}
