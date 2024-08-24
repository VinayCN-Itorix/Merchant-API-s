package com.merchant.banking.controller;

import io.apiwiz.compliance.config.EnableCompliance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@EnableCompliance
@RequestMapping("/merchant-locations")
public class LocationService {

@PostMapping
public ResponseEntity<?> createLocation(@RequestBody Map<String, Object> request) {
    Map<String, Object> website = Map.of(
            "id", "8d9a7125-805f-40f3-a405-bc89765db996",
            "name", "Grocery website",
            "type", "online",
            "details", Map.of(
                    "domain", "example.com"
            )
    );
    return new ResponseEntity<>(website, HttpStatus.CREATED);
}

@GetMapping
public ResponseEntity<?> retrieveLocationList() {
    List<Map<String, Object>> websites = List.of(
            Map.of(
                    "id", "8d9a7125-805f-40f3-a405-bc89765db996",
                    "name", "Grocery website",
                    "type", "online",
                    "details", Map.of(
                            "domain", "groceries.example.com"
                    )
            ),
            Map.of(
                    "id", "066223df-d5a8-42f0-b3ce-688c7a76f9a8",
                    "name", "Cars website",
                    "type", "online",
                    "details", Map.of(
                            "domain", "cars.example.com"
                    )
            )
    );
    return new ResponseEntity<>(websites, HttpStatus.OK);
}

@GetMapping("/{location_id}")
public ResponseEntity<?> retrieveLocation(@PathVariable("location_id") String locationId) {
    Map<String, Object> website = Map.of(
            "id", "8d9a7125-805f-40f3-a405-bc89765db996",
            "name", "Grocery website",
            "type", "online",
            "details", Map.of(
                    "domain", "groceries.example.com"
            )
    );
    return new ResponseEntity<>(website, HttpStatus.OK);
}

@DeleteMapping("/{location_id}")
public ResponseEntity<?> deleteLocation(@PathVariable("location_id") String locationId) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@PatchMapping("/{location_id}")
public ResponseEntity<?> updateLocation(@PathVariable("location_id") String locationId,
                                        @RequestBody Map<String, Object> request) {
    Map<String, Object> website = Map.of(
            "id", "8d9a7125-805f-40f3-a405-bc89765db996",
            "name", "Cars website - Name update",
            "type", "online",
            "details", Map.of(
                    "domain", "cars.example.com"
            )
    );
    return new ResponseEntity<>(website,HttpStatus.ACCEPTED);
}
}
