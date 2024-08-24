package com.merchant.banking.controller;

import io.apiwiz.compliance.config.EnableCompliance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableCompliance
@RequestMapping("/merchant-payment/api")
public class PaymentService {

@PostMapping("/orders/{order_id}/payments")
public ResponseEntity<?> payForOrder(@PathVariable("order_id") String orderId,
                                     @RequestHeader("Revolut-Api-Version") String apiVersion,
                                     @RequestBody Map<String, Object> request) {
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