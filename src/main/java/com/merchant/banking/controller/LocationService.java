package com.merchant.banking.controller;

import io.apiwiz.compliance.config.EnableCompliance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableCompliance
@RequestMapping("/merchant-locations/api/locations")
public class LocationService {

@Value("${api.create.customer:null}")
private String createCustomerUrl ;

@Autowired
private RestTemplate restTemplate;

@PostMapping
public ResponseEntity<?> createLocation(@RequestBody Map<String, Object> request , @RequestHeader(value = "enableTracing", required = false) boolean enableTracing,
                                        @RequestHeader(value = "deviate", required = false) boolean deviate,
                                        @RequestHeader(value = "enableComplianceAndTracing",required = false) boolean enableComplianceAndTracing,
                                        @RequestHeader(value = "enableLogs",required = false) boolean enableLogs) throws URISyntaxException {
    Map<String, Object> website = Map.of(
            "id", "8d9a7125-805f-40f3-a405-bc89765db996",
            "name", "Grocery website",
            "type", "online",
            "details", Map.of(
                    "domain", "example.com"
            )
    );
    if(enableTracing){
            HttpHeaders headers = new HttpHeaders();
            headers.add("enableTracing",String.valueOf(Boolean.TRUE));
            headers.add("deviate",String.valueOf(deviate));
            headers.add("Content-Type","application/json");
            headers.add("enableComplianceAndTracing",String.valueOf(enableComplianceAndTracing));
            headers.add("enableLogs",String.valueOf(enableLogs));
        
        Map<String, Object> customerInfo = new LinkedHashMap<String, Object>() {{
            put("full_name", "Example Customer");
            put("business_name", "Example Business");
            put("email", "example.customer@example.com");
            put("phone", "+441234567890");
        }};
            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(customerInfo, headers);
            restTemplate.exchange(new URI(createCustomerUrl), HttpMethod.POST,httpEntity,Object.class);
    }
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
