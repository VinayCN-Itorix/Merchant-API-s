package com.merchant.banking.controller;

import io.apiwiz.compliance.config.EnableCompliance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@EnableCompliance
@RequestMapping("/merchant-order/api")
class OrderController {

@PostMapping("/orders")
public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> request) {
    Map<String, Object> payment = Map.ofEntries(
            Map.entry("id", "6516e61c-d279-a454-a837-bc52ce55ed49"),
            Map.entry("token", "0adc0e3c-ab44-4f33-bcc0-534ded7354ce"),
            Map.entry("type", "payment"),
            Map.entry("state", "pending"),
            Map.entry("created_at", "2023-09-29T14:58:36.079398Z"),
            Map.entry("updated_at", "2023-09-29T14:58:36.079398Z"),
            Map.entry("amount", 500),
            Map.entry("currency", "GBP"),
            Map.entry("outstanding_amount", 500),
            Map.entry("capture_mode", "manual"),
            Map.entry("checkout_url", "https://checkout.revolut.com/payment-link/0adc0e3c-ab44-4f33-bcc0-534ded7354ce"),
            Map.entry("enforce_challenge", "automatic")
    );
    return new ResponseEntity<>(payment, HttpStatus.CREATED);
}

@GetMapping("/orders/{order_id}")
public ResponseEntity<?> retrieveOrder(@PathVariable("order_id") String orderId,
                                       @RequestHeader(value = "Revolut-Api-Version", required = false) String apiVersion) {
    Map<String, Object> payment = Map.ofEntries(
            Map.entry("id", "6516f565-c903-ae7d-8582-0bc70468ec29"),
            Map.entry("token", "579462b3-da89-4a46-8690-572d3968573f"),
            Map.entry("type", "payment"),
            Map.entry("state", "completed"),
            Map.entry("created_at", "2023-09-29T16:03:49.569437Z"),
            Map.entry("updated_at", "2023-09-29T16:04:30.727004Z"),
            Map.entry("amount", 500),
            Map.entry("currency", "GBP"),
            Map.entry("refunded_amount", 0),
            Map.entry("outstanding_amount", 0),
            Map.entry("capture_mode", "automatic"),
            Map.entry("enforce_challenge", "automatic"),
            Map.entry("payments", List.of(
                    Map.ofEntries(
                            Map.entry("id", "6516f587-1fd2-ac9c-9168-474c5269a9ee"),
                            Map.entry("state", "completed"),
                            Map.entry("created_at", "2023-09-29T16:04:23.931018Z"),
                            Map.entry("updated_at", "2023-09-30T16:07:44.169104Z"),
                            Map.entry("amount", 500),
                            Map.entry("currency", "GBP"),
                            Map.entry("settled_amount", 466),
                            Map.entry("settled_currency", "GBP"),
                            Map.entry("risk_level", "low"),
                            Map.entry("fees", List.of(
                                    Map.ofEntries(
                                            Map.entry("type", "acquiring"),
                                            Map.entry("amount", 34),
                                            Map.entry("currency", "GBP")
                                    )
                            )),
                            Map.entry("payment_method", Map.ofEntries(
                                    Map.entry("type", "card"),
                                    Map.entry("card_brand", "mastercard"),
                                    Map.entry("funding", "credit"),
                                    Map.entry("card_country_code", "MT"),
                                    Map.entry("card_bin", "542071"),
                                    Map.entry("card_last_four", "0016"),
                                    Map.entry("card_expiry", "12/28"),
                                    Map.entry("cardholder_name", "Example Holder"),
                                    Map.entry("checks", Map.ofEntries(
                                            Map.entry("three_ds", Map.ofEntries(
                                                    Map.entry("eci", "05"),
                                                    Map.entry("state", "verified"),
                                                    Map.entry("version", 2)
                                            )),
                                            Map.entry("cvv_verification", "match")
                                    ))
                            ))
                    )
            )),
            Map.entry("customer", Map.ofEntries(
                    Map.entry("id", "53825499-5658-4b28-b1ab-48ad1e47f776"),
                    Map.entry("email", "example@example.com")
            ))
    );
    return new ResponseEntity<>(payment, HttpStatus.OK);
}

@PatchMapping("/orders/{order_id}")
public ResponseEntity<?> updateOrder(@PathVariable("order_id") String orderId,
                                     @RequestBody Map<String, Object> request,
                                     @RequestHeader(value = "Revolut-Api-Version", required = false) String apiVersion) {
    Map<String, Object> payment = Map.ofEntries(
            Map.entry("id", "651a941a-02ef-af6f-9b6c-458c652e2c6a"),
            Map.entry("token", "0aa685ee-8d86-441d-bedd-3f7fbf41731b"),
            Map.entry("type", "payment"),
            Map.entry("state", "pending"),
            Map.entry("created_at", "2023-10-02T09:57:46.498026Z"),
            Map.entry("updated_at", "2023-10-02T11:54:46.648414Z"),
            Map.entry("amount", 1000),
            Map.entry("currency", "EUR"),
            Map.entry("outstanding_amount", 1000),
            Map.entry("settlement_currency", "GBP"),
            Map.entry("capture_mode", "manual"),
            Map.entry("cancel_authorised_after", "P3D"),
            Map.entry("checkout_url", "https://checkout.revolut.com/payment-link/0aa685ee-8d86-441d-bedd-3f7fbf41731b"),
            Map.entry("metadata", Map.ofEntries(
                    Map.entry("example_key", "example_value")
            )),
            Map.entry("enforce_challenge", "automatic"),
            Map.entry("merchant_order_data", Map.ofEntries(
                    Map.entry("url", "https://example.com/orders/12345")
            ))
    );
    return new ResponseEntity<>(payment, HttpStatus.ACCEPTED);
}

@GetMapping("/1.0/order")
public ResponseEntity<?> retrieveOrderList(@RequestParam String limit,
                                           @RequestParam String from_created_date,
                                           @RequestParam(required = false) String created_before,
                                           @RequestParam(required = false) String to_created_date,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) String merchant_order_ext_ref,
                                           @RequestParam(required = false) String state) {
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
            ),
            Map.ofEntries(
                    Map.entry("id", "f3c5e3f1-f73a-4853-a9e3-b0261301c66a"),
                    Map.entry("type", "PAYMENT"),
                    Map.entry("state", "COMPLETED"),
                    Map.entry("created_at", "2021-02-10T16:58:47.507560Z"),
                    Map.entry("updated_at", "2021-02-10T16:59:52.847017Z"),
                    Map.entry("completed_at", "2021-02-10T16:59:52.847017Z"),
                    Map.entry("description", "URBAN 'Panther' Watch by José Almeida"),
                    Map.entry("capture_mode", "AUTOMATIC"),
                    Map.entry("merchant_order_ext_ref", "testorder123"),
                    Map.entry("customer_id", "31345442-3d03-4c4b-8354-3bdaf0ca9600"),
                    Map.entry("email", "someothermail@gmail.com"),
                    Map.entry("order_amount", Map.ofEntries(
                            Map.entry("value", 777),
                            Map.entry("currency", "GBP")
                    )),
                    Map.entry("order_outstanding_amount", Map.ofEntries(
                            Map.entry("value", 0),
                            Map.entry("currency", "GBP")
                    ))
            )
    );
    
    return new ResponseEntity<>(payments, HttpStatus.OK);
}

@PostMapping("/orders/{order_id}/capture")
public ResponseEntity<?> captureOrder(@PathVariable("order_id") String orderId,
                                      @RequestBody Map<String, Object> request) {
    Map<String, Object> payment = Map.ofEntries(
            Map.entry("id", "65c4c739-113d-a608-9128-47c7ca90cbe3"),
            Map.entry("token", "ebc06202-061e-4d0f-8063-99195fad31fb"),
            Map.entry("type", "payment"),
            Map.entry("state", "completed"),
            Map.entry("created_at", "2024-02-08T12:21:13.022871Z"),
            Map.entry("updated_at", "2024-02-08T12:21:52.194601Z"),
            Map.entry("amount", 100),
            Map.entry("currency", "GBP"),
            Map.entry("refunded_amount", 0),
            Map.entry("outstanding_amount", 0),
            Map.entry("capture_mode", "manual"),
            Map.entry("payments", List.of(
                    Map.ofEntries(
                            Map.entry("id", "65c4c748-bf0d-af8a-9d69-0fc92bc0ff94"),
                            Map.entry("state", "captured"),
                            Map.entry("created_at", "2024-02-08T12:21:28.803165Z"),
                            Map.entry("updated_at", "2024-02-08T12:21:52.191352Z"),
                            Map.entry("token", "74af5a2b-6722-4353-aaf1-cd5926883b60"),
                            Map.entry("amount", 100),
                            Map.entry("currency", "GBP"),
                            Map.entry("settled_amount", 100),
                            Map.entry("settled_currency", "GBP"),
                            Map.entry("billing_address", Map.ofEntries(
                                    Map.entry("country_code", "US"),
                                    Map.entry("postcode", "12345")
                            )),
                            Map.entry("risk_level", "low"),
                            Map.entry("fees", List.of()), // Empty list for fees
                            Map.entry("payment_method", Map.ofEntries(
                                    Map.entry("type", "revolut_pay_card"),
                                    Map.entry("card_brand", "visa"),
                                    Map.entry("funding", "debit"),
                                    Map.entry("card_country_code", "US"),
                                    Map.entry("card_bin", "529999"),
                                    Map.entry("card_last_four", "0368"),
                                    Map.entry("card_expiry", "12/28"),
                                    Map.entry("cardholder_name", "Test Holder"),
                                    Map.entry("checks", Map.ofEntries(
                                            Map.entry("three_ds", Map.ofEntries(
                                                    Map.entry("state", "verified"),
                                                    Map.entry("version", 2)
                                            ))
                                    ))
                            ))
                    )
            )),
            Map.entry("enforce_challenge", "automatic")
    );
    return new ResponseEntity<>(payment, HttpStatus.CREATED);
}

@PostMapping("/1.0/orders/{order_id}/cancel")
public ResponseEntity<?> cancelOrder(@PathVariable("order_id") String orderId,
                                     @RequestHeader(value = "Revolut-Api-Version", required = false) String apiVersion) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@PostMapping("/1.0/orders/{order_id}/refund")
public ResponseEntity<?> refundOrder(@PathVariable("order_id") String orderId,
                                     @RequestBody Map<String, Object> request) {
    Map<String, Object> refund = Map.ofEntries(
            Map.entry("id", "6a1353a8-3054-40ee-ab39-97a11e4c5f2a"),
            Map.entry("type", "REFUND"),
            Map.entry("state", "COMPLETED"),
            Map.entry("created_at", "2020-05-12T14:23:11.046526Z"),
            Map.entry("updated_at", "2020-05-12T14:23:11.046526Z"),
            Map.entry("completed_at", "2020-05-12T14:23:11.046526Z"),
            Map.entry("order_amount", Map.ofEntries(
                    Map.entry("value", 40),
                    Map.entry("currency", "GBP")
            )),
            Map.entry("email", "customer@gmail.com"),
            Map.entry("related", List.of(
                    Map.ofEntries(
                            Map.entry("id", "4695b666-45d0-4f15-ad10-e66a84c914bf"),
                            Map.entry("type", "PAYMENT"),
                            Map.entry("amount", Map.ofEntries(
                                    Map.entry("value", 100),
                                    Map.entry("currency", "GBP")
                            ))
                    )
            ))
    );
    return new ResponseEntity<>(refund, HttpStatus.CREATED);
}

@PostMapping("/orders/{order_id}/payments")
public ResponseEntity<?> payForOrder(@PathVariable("order_id") String orderId,
                                     @RequestBody Map<String, Object> request,
                                     @RequestHeader(value = "Revolut-Api-Version", required = false) String apiVersion,
                                     @RequestHeader(value= "deviate", required = false) boolean deviate) {
    if(deviate){
        Map<String, Object> payment = Map.of(
                "id", "63c55e04-4208-a43d-9c96-eaee848ffbaf",
                "order_id", "63c55df6-1461-a886-b90f-f49d3c370253",
                "payment_method", Map.of(
                        "type", "card",
                        "id", "2b83c23a-650e-40c3-8989-00ee24478738",
                        "brand", "mastercard_credit",
                        "last_four", 1234
                ),
                "state", "authorisation_failed"
        );
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    Map<String, Object> payment = Map.of(
            "id", "63c55e04-4208-a43d-9c96-eaee848ffbaf",
            "order_id", "63c55df6-1461-a886-b90f-f49d3c370253",
            "payment_method", Map.of(
                    "type", "card",
                    "id", "2b83c23a-650e-40c3-8989-00ee24478738",
                    "brand", "mastercard_credit",
                    "last_four", 1234
            ),
            "state", "authorisation_passed"
    );
    return new ResponseEntity<>(payment, HttpStatus.OK);
}
}