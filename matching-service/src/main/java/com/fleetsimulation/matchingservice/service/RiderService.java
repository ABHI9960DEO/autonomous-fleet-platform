package com.fleetsimulation.matchingservice.service;

import com.fleetsimulation.matchingservice.domain.Ride;
import com.fleetsimulation.matchingservice.dto.RideRequest;
import com.fleetsimulation.matchingservice.dto.RideResponse;
import com.fleetsimulation.matchingservice.dto.RideStatusDto;
import com.fleetsimulation.matchingservice.kafka.RideRequestProducer;
import com.fleetsimulation.matchingservice.repository.RideRepository;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RiderService {
    private final RideRepository rideRepository;
    private final RideRequestProducer rideRequestProducer;

    public RiderService(RideRepository rideRepository, RideRequestProducer rideRequestProducer) {
        this.rideRepository = rideRepository;
        this.rideRequestProducer = rideRequestProducer;
    }

    public RideResponse createRide (RideRequest rideRequest) {
        String rideId = "ride-" + UUID.randomUUID();
        Ride ride = new Ride();
        ride.setRideId(rideId);
        ride.setRiderId(rideRequest.riderId);
        ride.setPickupLat(rideRequest.pickUpLat);
        ride.setPickupLng(rideRequest.pickUpLon);
        ride.setDropOffLat(rideRequest.dropoffLat);
        ride.setDropOffLng(rideRequest.dropoffLon);

        //Publishing the ride request event (payload)
        var evt = new java.util.LinkedHashMap<String, Object>();

        evt.put("rideId", ride.getRideId());
        evt.put("riderId", ride.getRiderId());
        evt.put("pickupLat", ride.getPickupLat());
        evt.put("pickupLng", ride.getPickupLng());
        evt.put("dropOffLat", ride.getDropOffLat());
        evt.put("dropOffLng", ride.getDropOffLng());
        evt.put("requestedAt", System.currentTimeMillis());
        rideRequestProducer.publish(evt);
        //rideRepository.save(ride);
        return new RideResponse(ride.getRideId(), ride.getStatus().name(), ride.getVehicleId());
    }

    public RideStatusDto getStatus(String rideId) {
        Optional<Ride> r = rideRepository.findById(rideId);

        return r.map(ride -> new RideStatusDto(ride.getRideId(), ride.getStatus().name(), ride.getVehicleId())).orElse(null);
    }
}
