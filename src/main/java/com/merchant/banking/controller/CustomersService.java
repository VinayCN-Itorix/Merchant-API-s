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
@RequestMapping("/merchant-customer/api/1.0/customers")
public class CustomersService {

@Value("${api.create.order:null}")
private String createOrder ;
@Autowired
private RestTemplate restTemplate;
@PostMapping
public ResponseEntity<?> createCustomer(@RequestBody Map<String, Object> customerRequest,
                                        @RequestHeader(value = "enableTracing", required = false) boolean enableTracing,
                                        @RequestHeader(value = "deviate", required = false) boolean deviate) throws URISyntaxException  {
    Map<String, Object> customer = Map.ofEntries(
            Map.entry("id", "6c7c97a8-cfc1-4cf3-8b38-26a74fdf1fae"),
            Map.entry("full_name", "Example Customer"),
            Map.entry("business_name", "Example Business"),
            Map.entry("email", "example.customer@example.com"),
            Map.entry("phone", "+441234567890"),
            Map.entry("created_at", "2020-06-24T12:03:39.979397Z"),
            Map.entry("updated_at", "2020-06-24T12:03:39.979397Z")
    );
    if(enableTracing){
        HttpHeaders headers = new HttpHeaders();
        headers.add("enableTracing",String.valueOf(Boolean.TRUE));
        headers.add("deviate",String.valueOf(deviate));
        headers.add("Content-Type","application/json");
        
        Map<String, Object> orderInfo = new LinkedHashMap<String, Object>() {{
           put("amount","500");
           put("currency","GBP");
        }};
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(orderInfo, headers);
        restTemplate.exchange(new URI(createOrder), HttpMethod.POST,httpEntity,Object.class);
    }
    return new ResponseEntity<>(customer, HttpStatus.CREATED);
}

@GetMapping
public ResponseEntity<?> getCustomerList() {
    List<Map<String, Object>> customers = List.of(
            Map.ofEntries(
                    Map.entry("id", "9dfb8491-bfb0-4420-ad63-0fa7bdd3dffb"),
                    Map.entry("full_name", "First Customer"),
                    Map.entry("email", "first.customer@example.com"),
                    Map.entry("created_at", "2020-06-24T12:12:56.596703Z"),
                    Map.entry("updated_at", "2020-06-24T12:12:56.737082Z")
            ),
            Map.ofEntries(
                    Map.entry("id", "6c7c97a8-cfc1-4cf3-8b38-26a74fdf1fae"),
                    Map.entry("full_name", "Second Customer"),
                    Map.entry("business_name", "Second Business"),
                    Map.entry("email", "second.customer@example.com"),
                    Map.entry("phone", "+441234567890"),
                    Map.entry("created_at", "2020-06-24T12:03:39.979397Z"),
                    Map.entry("updated_at", "2020-06-25T10:03:39.134417Z")
            ),
            Map.ofEntries(
                    Map.entry("id", "014f0ad6-c45b-4d7d-83c6-80eea94fceac"),
                    Map.entry("full_name", "Third Customer"),
                    Map.entry("email", "third.customer@example.com"),
                    Map.entry("phone", "+441234567890"),
                    Map.entry("created_at", "2020-06-23T14:13:08.262336Z"),
                    Map.entry("updated_at", "2020-06-24T10:47:11.173027Z")
            )
    );
    return new ResponseEntity<>(customers, HttpStatus.OK);
}

@GetMapping("/{customer_id}")
public ResponseEntity<?> getCustomerById(@PathVariable String customer_id) {
    Map<String, Object> customer = Map.ofEntries(
            Map.entry("id", "6c7c97a8-cfc1-4cf3-8b38-26a74fdf1fae"),
            Map.entry("full_name", "Example Customer"),
            Map.entry("business_name", "Example Business"),
            Map.entry("email", "example.customer@example.com"),
            Map.entry("phone", "+441234567890"),
            Map.entry("created_at", "2020-06-24T12:03:39.979397Z"),
            Map.entry("updated_at", "2020-06-25T10:03:39.134417Z"),
            Map.entry("payment_methods", List.of(
                    Map.ofEntries(
                            Map.entry("id", "648334a8-9546-a983-a81a-efc6d5bdd0be"),
                            Map.entry("type", "REVOLUT_PAY"),
                            Map.entry("saved_for", "MERCHANT"),
                            Map.entry("method_details", Map.ofEntries(
                                    Map.entry("created_at", "2023-06-09T14:18:16.577888Z")
                            ))
                    ),
                    Map.ofEntries(
                            Map.entry("id", "edef3ba4-60a0-4df3-8f12-e5fc858c2420"),
                            Map.entry("type", "CARD"),
                            Map.entry("saved_for", "CUSTOMER"),
                            Map.entry("method_details", Map.ofEntries(
                                    Map.entry("bin", "459765"),
                                    Map.entry("last4", "6578"),
                                    Map.entry("expiry_month", 2),
                                    Map.entry("expiry_year", 2025),
                                    Map.entry("cardholder_name", "Example Customer"),
                                    Map.entry("brand", "VISA"),
                                    Map.entry("funding", "DEBIT"),
                                    Map.entry("issuer", "EXAMPLE ISSUER"),
                                    Map.entry("issuer_country", "GB"),
                                    Map.entry("billing_address", Map.ofEntries(
                                            Map.entry("street_line_1", "7, Westferry Circus"),
                                            Map.entry("street_line_2", "Columbus Building"),
                                            Map.entry("post_code", "E144HD"),
                                            Map.entry("city", "London"),
                                            Map.entry("region", "Greater London"),
                                            Map.entry("country_code", "GB")
                                    )),
                                    Map.entry("created_at", "2023-03-24T14:15:22Z")
                            ))
                    )
            ))
    );
    return new ResponseEntity<>(customer, HttpStatus.OK);
}

@PatchMapping("/{customer_id}")
public ResponseEntity<?> updateCustomer(@PathVariable String customer_id, @RequestBody Map<String, Object> customerRequest) {
    Map<String, Object> customer = Map.ofEntries(
            Map.entry("id", "6c7c97a8-cfc1-4cf3-8b38-26a74fdf1fae"),
            Map.entry("full_name", "Example Customer"),
            Map.entry("business_name", "Example Business"),
            Map.entry("email", "example.business@example.com"),
            Map.entry("phone", "+441234567890"),
            Map.entry("created_at", "2020-06-24T12:03:39.979397Z"),
            Map.entry("updated_at", "2020-06-25T10:03:39.134417Z")
    );
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
}

@DeleteMapping("/{customer_id}")
public ResponseEntity<?> deleteCustomer(@PathVariable String customer_id) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@GetMapping("/{customer_id}/payment-methods")
public ResponseEntity<?> getPaymentMethods(@PathVariable String customer_id,
                                           @RequestParam(value = "only_merchant", required = false) String onlyMerchant) {
    List<Map<String, Object>> paymentMethods = List.of(
            Map.ofEntries(
                    Map.entry("id", "648334a8-9546-a983-a81a-efc6d5bdd0be"),
                    Map.entry("type", "REVOLUT_PAY"),
                    Map.entry("saved_for", "MERCHANT"),
                    Map.entry("method_details", Map.ofEntries(
                            Map.entry("created_at", "2023-06-09T14:18:16.577888Z")
                    ))
            ),
            Map.ofEntries(
                    Map.entry("id", "edef3ba4-60a0-4df3-8f12-e5fc858c2420"),
                    Map.entry("type", "CARD"),
                    Map.entry("saved_for", "CUSTOMER"),
                    Map.entry("method_details", Map.ofEntries(
                            Map.entry("bin", "459678"),
                            Map.entry("last4", "6896"),
                            Map.entry("expiry_month", 3),
                            Map.entry("expiry_year", 2025),
                            Map.entry("cardholder_name", "Example Customer"),
                            Map.entry("brand", "VISA"),
                            Map.entry("funding", "DEBIT"),
                            Map.entry("issuer", "EXAMPLE ISSUER"),
                            Map.entry("issuer_country", "GB"),
                            Map.entry("billing_address", Map.ofEntries(
                                    Map.entry("street_line_1", "7"),
                                    Map.entry("street_line_2", "Westferry Circus"),
                                    Map.entry("post_code", "E144HD"),
                                    Map.entry("city", "London"),
                                    Map.entry("region", "Greater London"),
                                    Map.entry("country_code", "GB")
                            )),
                            Map.entry("created_at", "2023-03-24T14:15:22Z")
                    ))
            ),
            Map.ofEntries(
                    Map.entry("id", "a04406c4-05be-498b-8207-cc1e02a9b3ca"),
                    Map.entry("type", "CARD"),
                    Map.entry("saved_for", "MERCHANT"),
                    Map.entry("method_details", Map.ofEntries(
                            Map.entry("bin", "459885"),
                            Map.entry("last4", "7653"),
                            Map.entry("expiry_month", 12),
                            Map.entry("expiry_year", 2021),
                            Map.entry("cardholder_name", "Example Holder"),
                            Map.entry("brand", "MASTERCARD"),
                            Map.entry("funding", "CREDIT"),
                            Map.entry("issuer", "EXAMPLE ISSUER"),
                            Map.entry("issuer_country", "GB"),
                            Map.entry("billing_address", Map.ofEntries(
                                    Map.entry("street_line_1", "Revolut"),
                                    Map.entry("street_line_2", "1 Canada Square"),
                                    Map.entry("post_code", "EC2V 6DN"),
                                    Map.entry("city", "London"),
                                    Map.entry("region", "Greater London"),
                                    Map.entry("country_code", "GB")
                            )),
                            Map.entry("created_at", "2023-03-24T14:15:22Z")
                    ))
            )
    );
    return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
}

@GetMapping("/{customer_id}/payment-methods/{payment_method_id}")
public ResponseEntity<?> getPaymentMethodById(@PathVariable String customer_id,
                                              @PathVariable String payment_method_id) {
    Map<String, Object> paymentMethod = Map.ofEntries(
            Map.entry("id", "edef3ba4-60a0-4df3-8f12-e5fc858c2420"),
            Map.entry("type", "CARD"),
            Map.entry("saved_for", "CUSTOMER"),
            Map.entry("method_details", Map.ofEntries(
                    Map.entry("bin", "459678"),
                    Map.entry("last4", "6896"),
                    Map.entry("expiry_month", 3),
                    Map.entry("expiry_year", 2025),
                    Map.entry("cardholder_name", "Example Customer"),
                    Map.entry("brand", "VISA"),
                    Map.entry("funding", "DEBIT"),
                    Map.entry("issuer", "EXAMPLE ISSUER"),
                    Map.entry("issuer_country", "GB"),
                    Map.entry("billing_address", Map.ofEntries(
                            Map.entry("street_line_1", "7"),
                            Map.entry("street_line_2", "Westferry Circus"),
                            Map.entry("post_code", "E144HD"),
                            Map.entry("city", "London"),
                            Map.entry("region", "Greater London"),
                            Map.entry("country_code", "GB")
                    )),
                    Map.entry("created_at", "2023-03-24T14:15:22Z")
            ))
    );
    return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
}

@PatchMapping("/{customer_id}/payment-methods/{payment_method_id}")
public ResponseEntity<?> updatePaymentMethod(@PathVariable String customer_id,
                                             @PathVariable String payment_method_id,
                                             @RequestBody Map<String, Object> paymentMethodRequest) {
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
}

@DeleteMapping("/{customer_id}/payment-methods/{payment_method_id}")
public ResponseEntity<?> deletePaymentMethod(@PathVariable String customer_id,
                                             @PathVariable String payment_method_id) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
}
