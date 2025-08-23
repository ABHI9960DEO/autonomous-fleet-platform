package com.fleetsimulation.matchingservice.config;

public class KafkaTopics {
    //Singleton design pattern
    private KafkaTopics() {

    }
    public static final String RIDE_REQUESTED_V1 = "ride_requested_v1";
    public static final String RIDE_MATCHED_V1 = "ride_matched_v1";
    public static final String VEHICLE_TELEMETRY_V1 = "vehicle_telemetry_v1";
    public static final String TRIP_EVENTS_V1 = "trip_events_v1";
}
