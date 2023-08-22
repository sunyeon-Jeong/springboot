package com.mallang.mallangshopbeta.naver.service;

import com.mallang.mallangshopbeta.naver.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


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
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, httpHeaders);

        // HttpResponse -> query + GET메서드 + Request(header+body)
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://openapi.naver.com/v1/search/shop.json?display=15&query="
                        + query, HttpMethod.GET, requestEntity, String.class);
        // display=15 : 검색결과 총 15개 show
        // HttpEntity 클래스 : Http 요청, 응답에 해당하는 HttpHeader와 HttpBody를 가짐
        // HttpEntity 클래스를 상속받아 구현한 클래스 -> requestEntity, responseEntity

        // HttpStatus
        HttpStatusCode httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        log.info("NAVER API Status Code : " + status);

        // Response Body -> method
        // responseEntity의 바디에 있는 값을 -> String 타입 response에 담아옴
        String response = responseEntity.getBody();

        return fromJSONtoItems(response);

    }

    // String(검색결과) -> Dto에 담기위해 변환하는 메서드
    public List<ItemDto> fromJSONtoItems(String response) {

        // JSONObject -> build.gradle에 종속성추가
        JSONObject jsonObject = new JSONObject(response); // String 검색결과 -> JSONObject
        JSONArray items = jsonObject.getJSONArray("items"); // JSONObject -> JSONArray

        // Dto List 객체생성
        List<ItemDto> itemDtoList = new ArrayList<>();

        // JSONArray -> for문 돌면서 item 하나씩 꺼내 -> ItemDto에 변환
        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = ItemDto.of(itemJson);
            itemDtoList.add(itemDto);
        }

        return itemDtoList;

    }

}