package com.mallang.mallangshopbeta.naver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class NaverApiService {

    public List<ItemDto> searchItems(String query) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();

        // HttpHeader -> Client Id/Pw 실어 보냄
        httpHeaders.add("X-Naver-Client-Id", "_VwwboxD7N9gzc6pnvuC");
        httpHeaders.add("X-Naver-Client-Secret", "e3kEsesnyl");
        String body = "";

        // HttpEntity -> header + body
        HttpEntity<String> requestEntity = new HttpEntity<>(body, httpHeaders);

        // HttpResponse -> query + GET메서드 + Request(header+body)
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://openapi.naver.com/v1/search/shop.json?display=15&query="
                        + query, HttpMethod.GET, requestEntity, String.class);

        // HttpStatus
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        log.info("NAVER API Status Code : " + status);

        // Response Body -> method
        String response = responseEntity.getBody();

        return fromJSONtoItems(response);

    }

}