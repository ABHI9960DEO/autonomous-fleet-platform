package com.fleetsimulation.simulatorservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.fleetsimulation.simulatorservice.config.KafkaTopics.RIDE_MATCHED_V1;

@Component
public class RideMatchedProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RideMatchedProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String rideId, String vehicleId, double score) {
        try{
            Map<String, Object> evt = new LinkedHashMap<>();
            evt.put("rideId", rideId);
            evt.put("vehicleId", vehicleId);
            evt.put("matchedAt", System.currentTimeMillis());
            evt.put("matchScore", score);

            kafkaTemplate.send(RIDE_MATCHED_V1, rideId, objectMapper.writeValueAsString(evt));
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish ride_matched_v1", e);
        }
    }
}
