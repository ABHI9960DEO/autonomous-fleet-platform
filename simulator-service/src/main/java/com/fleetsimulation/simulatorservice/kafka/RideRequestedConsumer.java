package com.fleetsimulation.simulatorservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetsimulation.simulatorservice.service.MatchingEngine;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

import static com.fleetsimulation.simulatorservice.config.KafkaTopics.RIDE_REQUESTED_V1;

@Component
public class RideRequestedConsumer {
    private final MatchingEngine engine;
    private final RideMatchedProducer producer;
    private final ObjectMapper mapper = new ObjectMapper();

    public RideRequestedConsumer(MatchingEngine engine, RideMatchedProducer producer) {
        this.engine = engine;
        this.producer = producer;
    }

    @KafkaListener(topics = RIDE_REQUESTED_V1, groupId = "simulator-service")
    public void onRideRequested(ConsumerRecord<String, String> record) throws Exception {
        Map<String, Object> evt = mapper.readValue(record.value(), Map.class);
        String rideId = (String) evt.get("rideId");
        double lat = ((Number)evt.get("pickupLat")).doubleValue();
        double lng = ((Number)evt.get("pickupLng")).doubleValue();

        Optional<MatchingEngine.Vehicle> v = engine.findNearestVehicle(lat, lng);

        if(v.isPresent()) {
            engine.assign(v.get().id);
            producer.publish(rideId, v.get().id, 0.75);
        }
    }


}
