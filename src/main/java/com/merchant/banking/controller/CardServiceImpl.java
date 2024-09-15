package com.merchant.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;

@CrossOrigin
@Service
@Slf4j
public class CardServiceImpl implements CardService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<Object> createCard(Map<String, Object> headerParams, Map<String, Object> queryParams, String requestBody) throws Exception {
        String res = """
                {
                  "request_id": "7a10f3eb-fe56-4699-9bd0-044a63508828",
                  "virtual": true,
                  "holder_id": "173ab846-de2a-1234-5678-160bd2e660e6",
                  "label": "Kirby Janette",
                  "accounts": [
                    "75aa436d-2a04-4ab9-af14-ed0955769b8c"
                  ],
                  "categories": [
                    "groceries",
                    "restaurants"
                  ],
                  "spending_limits": {
                    "single": {
                      "amount": 200.22,
                      "currency": "GBP"
                    },
                    "week": {
                      "amount": 200.44,
                      "currency": "GBP"
                    }
                  }
                }
                """;
        Map<String,Object>  map = objectMapper.readValue(res, Map.class);
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<?> redirectCard(Map<String, Object> headerParams, Map<String, Object> queryParams, String requestBody) throws Exception {
        String res = """
                {
                  "id": "aa14a9af-d7a7-4214-a743-lok818f74bd0",
                  "last_digits": "2671",
                  "expiry": "09/2027",
                  "state": "active",
                  "holder_id": "173ab846-de2a-1234-5678-160bd2e660e6",
                  "label": "Kirby Janette",
                  "virtual": true,
                  "product": {
                    "code": "MBJ"
                  },
                  "accounts": [
                    "f52c6c84-26b9-4e95-bbcf-99ed6523fb51"
                  ],
                  "categories": [
                    "restaurants",
                    "groceries"
                  ],
                  "spend_program": {
                    "label": "Office supplies"
                  },
                  "spending_limits": {
                    "single": {
                      "amount": 200.22,
                      "currency": "GBP"
                    },
                    "month": {
                      "amount": 200.44,
                      "currency": "GBP"
                    }
                  },
                  "created_at": "2022-09-15T11:04:11.047305Z",
                  "updated_at": "2024-08-17T08:32:11.024721Z"
                }
                """;
        Map<String,Object>  map = objectMapper.readValue(res, Map.class);
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<?> getCard(Map<String, Object> headerParams, Map<String, Object> queryParams) throws Exception {
        if(queryParams != null && "true".equalsIgnoreCase(String.valueOf(queryParams.get("block")))){
            return new ResponseEntity(HttpStatusCode.valueOf(403));
        }
        String card = """
                {
                    "request_id": "7a10f3eb-fe56-4699-9bd0-044a63508828",
                    "virtual": true,
                    "holder_id": "173ab846-de2a-1234-5678-160bd2e660e6",
                    "label": "Kirby Janette",
                    "accounts": [
                      "75aa436d-2a04-4ab9-af14-ed0955769b8c"
                    ],
                    "categories": [
                      "groceries",
                      "restaurants"
                    ],
                    "spending_limits": {
                      "single": {
                        "amount": 200.22,
                        "currency": "GBP"
                      }
                    }
                  }
                """;
        Map<String,Object>  map = objectMapper.readValue(card, Map.class);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Requests-Remaining","0");
        headers.add("X-RateLimit-Limit","0");
        return new ResponseEntity<>(map,headers,HttpStatus.OK);
    }
}
