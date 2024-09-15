package com.merchant.banking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/v1/card")
public interface CardService {

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(
            @RequestHeader Map<String, Object> headerParams,
            @RequestParam Map<String, Object> queryParams,
            @RequestBody String requestBody
    ) throws Exception;

    @PostMapping("/redirect/*")
    public ResponseEntity<?> redirectCard(
            @RequestHeader Map<String, Object> headerParams,
            @RequestParam Map<String, Object> queryParams,
            @RequestBody String requestBody
    ) throws Exception;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCard(
            @RequestHeader Map<String, Object> headerParams,
            @RequestParam Map<String, Object> queryParams
    ) throws Exception;
}
