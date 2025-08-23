package com.fleetsimulation.simulatorservice.config;

import com.fleetsimulation.simulatorservice.service.MatchingEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SimAppConfig {
    @Bean
    public MatchingEngine matchingEngine() {
        return new MatchingEngine(List.of(
           new MatchingEngine.Vehicle("vehicle-1", 12.900, 77.600),
           new MatchingEngine.Vehicle("vehicle-2", 12.905, 77.610),
           new MatchingEngine.Vehicle("vehicle-3", 12.910,77.620)
        ));
    }
}
