package com.fleetsimulation.matchingservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetsimulation.matchingservice.config.KafkaTopics;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RideRequestProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public RideRequestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Object rideRequestedEvent) {
        try {
            String json = mapper.writeValueAsString(rideRequestedEvent);
            kafkaTemplate.send(KafkaTopics.RIDE_REQUESTED_V1, json);
        }catch (JsonProcessingException e) {
            throw new RuntimeException("Failded to serialize ride requested event", e);
        }
    }
}
