package com.fleetsimulation.simulatorservice.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MatchingEngine {
    public static class Vehicle {
        public String id;
        public double lat, lng;
        public boolean available = true;

        public Vehicle(String id, double lat, double lng) {
            this.id = id;
            this.lat = lat;
            this.lng = lng;
        }

        public Vehicle() {}
    }

    private final Map<String, Vehicle> vehicles = new ConcurrentHashMap<>();

    public MatchingEngine(Collection<Vehicle> seed) {
        seed.forEach(v -> this.vehicles.put(v.id, v));
    }

    public Optional<Vehicle> findNearestVehicle(double lat, double lng) {
        return vehicles.values().stream()
                .filter(v -> v.available)
                .min(Comparator.comparingDouble(v -> dist2(lat, lng, v.lat, v.lng)));
    }

    private static double dist2(double lat1, double lng1, double lat2, double lng2) {
        double dx = lat1 - lat2;
        double dy = lng1 - lng2;

        return dx * dx + dy * dy;
    }

    public void assign(String vehicleId) {
        var v = vehicles.get(vehicleId);
        if (v != null) v.available = false;
    }

    public void trickTelemetry(java.util.function.Consumer<Vehicle> sink) {
        vehicles.values().forEach(v -> {v.lat += (Math.random() - 0.5) * 0.001;
                                        v.lng += (Math.random() - 0.5) * 0.001;
                                        sink.accept(v);
        });
    }
}
