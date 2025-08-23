package com.fleetsimulation.matchingservice.repository;

import com.fleetsimulation.matchingservice.domain.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, String> {
}
