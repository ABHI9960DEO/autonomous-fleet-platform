package com.fleetsimulation.matchingservice.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalErrors {


        @ExceptionHandler(Exception.class)
        ResponseEntity<Map<String,Object>> onAny(Exception ex) {
            ex.printStackTrace(); // shows in console
            return ResponseEntity.status(500).body(Map.of(
                    "error","Internal Server Error",
                    "message", ex.getMessage()
            ));
       }
}

