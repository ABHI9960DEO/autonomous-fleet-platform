package com.fleetsimulation.simulatorservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetsimulation.simulatorservice.service.MatchingEngine;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import static com.fleetsimulation.simulatorservice.config.KafkaTopics.VEHICLE_TELEMETRY_V1;

@Component
public class TelemetryProducer {
    private final MatchingEngine engine;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TelemetryProducer(MatchingEngine engine, KafkaTemplate<String, String> kafkaTemplate) {
        this.engine = engine;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 2000)
    public void publish() {
        engine.trickTelemetry(v -> {
            try{
                Map<String, Object> t = new LinkedHashMap<>();
                t.put("vehicleId", v.id);
                t.put("lat", v.lat);
                t.put("lng", v.lng);
                t.put("speedKmph", 25 + Math.random()*15);
                t.put("headingDeg", Math.random() * 360);
                t.put("ts", System.currentTimeMillis());
                kafkaTemplate.send(VEHICLE_TELEMETRY_V1, objectMapper.writeValueAsString(t));
            }catch (Exception e){

            }
        });
    }
}
