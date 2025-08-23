package com.fleetsimulation.simulatorservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaAdminConfig {
    @Bean
    public NewTopic rideRequested() {
        return new NewTopic(KafkaTopics.RIDE_REQUESTED_V1, 6, (short) 1);
    }

    @Bean
    public NewTopic rideMatched() {
        return new NewTopic(KafkaTopics.RIDE_MATCHED_V1, 6, (short) 1);
    }

    @Bean
    public NewTopic vehicleTelemetry() {
        return new NewTopic(KafkaTopics.VEHICLE_TELEMETRY_V1, 6, (short) 1);
    }
}
