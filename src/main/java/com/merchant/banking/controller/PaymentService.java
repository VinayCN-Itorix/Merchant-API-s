package com.merchant.banking.controller;

import io.apiwiz.compliance.config.EnableCompliance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableCompliance
@RequestMapping("/merchant-payment/api")
public class PaymentService {
@Value("${api.capture.order:null}")
private String captureOrder ;
@Value("${api.cancel.order:null}")
private String cancelOrder ;
@Autowired
private RestTemplate restTemplate;

@PostMapping("/orders/{order_id}/payments")
public ResponseEntity<?> payForOrder(@PathVariable("order_id") String orderId,
                                     @RequestHeader("Revolut-Api-Version") String apiVersion,
                                     @RequestHeader(value = "deviate" , required = false) boolean deviate,
                                     @RequestBody Map<String, Object> request,
                                     @RequestHeader(value = "enableTracing", required = false) boolean enableTracing,
                                     @RequestHeader(value = "enableComplianceAndTracing",required = false) boolean enableComplianceAndTracing,
                                     @RequestHeader(value = "enableLogs",required = false) boolean enableLogs) throws URISyntaxException {
    if(deviate){
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("state","authorisation_failed");
        if(enableTracing){
            HttpHeaders headers = new HttpHeaders();
            headers.add("enableTracing",String.valueOf(Boolean.TRUE));
            headers.add("Revolut-Api-Version","new-3.2");
            headers.add("Content-Type","application/json");
            headers.add("enableComplianceAndTracing",String.valueOf(enableComplianceAndTracing));
            headers.add("enableLogs",String.valueOf(enableLogs));
            Map<String, Object> orderInfo = new LinkedHashMap<String, Object>() {{
                put("amount", "500");
                put("currency","GBP");
            }};
            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(orderInfo, headers);
            restTemplate.exchange(new URI(cancelOrder), HttpMethod.POST,httpEntity,Object.class);
        }
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
    if(enableTracing){
        HttpHeaders headers = new HttpHeaders();
        headers.add("enableTracing",String.valueOf(Boolean.TRUE));
        headers.add("enableComplianceAndTracing",String.valueOf(enableComplianceAndTracing));
        headers.add("enableLogs",String.valueOf(enableLogs));
        Map<String, Object> orderInfo = new LinkedHashMap<String, Object>() {{
            put("amount", "500");
        }};
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(orderInfo, headers);
        restTemplate.exchange(new URI(captureOrder), HttpMethod.POST,httpEntity,Object.class);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
}

@GetMapping("/payments/{payment_id}")
public ResponseEntity<?> retrievePaymentDetails(@PathVariable("payment_id") String paymentId,
                                                @RequestHeader("Revolut-Api-Version") String apiVersion) {
    List<Map<String, Object>> payments = List.of(
            Map.ofEntries(
                    Map.entry("id", "f0d685f4-07ab-4eff-ba80-5811303c607d"),
                    Map.entry("type", "PAYMENT"),
                    Map.entry("state", "PENDING"),
                    Map.entry("created_at", "2021-02-10T18:17:37.959383Z"),
                    Map.entry("updated_at", "2021-02-10T18:17:37.959383Z"),
                    Map.entry("order_amount", Map.ofEntries(
                            Map.entry("value", 35000),
                            Map.entry("currency", "GBP")
                    )),
                    Map.entry("order_outstanding_amount", Map.ofEntries(
                            Map.entry("value", 35000),
                            Map.entry("currency", "GBP")
                    ))
            ),
            Map.ofEntries(
                    Map.entry("id", "feca684a-b9ea-4033-9bc4-b9e6ac12ada6"),
                    Map.entry("type", "PAYMENT"),
                    Map.entry("state", "COMPLETED"),
                    Map.entry("created_at", "2021-02-10T16:59:23.642673Z"),
                    Map.entry("updated_at", "2021-02-10T16:59:50.886826Z"),
                    Map.entry("completed_at", "2021-02-10T16:59:50.886826Z"),
                    Map.entry("settlement_currency", "USD"),
                    Map.entry("email", "sally.gibson@lloydsbank.co.uk"),
                    Map.entry("order_amount", Map.ofEntries(
                            Map.entry("value", 1000),
                            Map.entry("currency", "GBP")
                    )),
                    Map.entry("order_outstanding_amount", Map.ofEntries(
                            Map.entry("value", 0),
                            Map.entry("currency", "GBP")
                    ))
            )
    );
    return new ResponseEntity<>(HttpStatus.CREATED);
    
}
}