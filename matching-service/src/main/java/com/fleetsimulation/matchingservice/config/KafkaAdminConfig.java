package com.fleetsimulation.matchingservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaAdminConfig {
    @Bean
    public NewTopic rideRequestTopic() {
        return new NewTopic(KafkaTopics.RIDE_REQUESTED_V1, 1, (short) 1);

    }

    @Bean
    public NewTopic rideMatchedTopic() {
        return new NewTopic(KafkaTopics.RIDE_MATCHED_V1, 1, (short) 1);
    }

    @Bean
    public NewTopic vehicleTelemetryTopic() {
        return new NewTopic(KafkaTopics.VEHICLE_TELEMETRY_V1, 1, (short) 1);
    }

//    @Bean
//    public NewTopic tripEventTopic() {
//        return new NewTopic(KafkaTopics.)
//    }
}
