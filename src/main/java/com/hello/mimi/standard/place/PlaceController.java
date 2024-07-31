package com.hello.mimi.standard.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/searchPlace")
    public String mapPlaceTest(){
        return "/place/searchPlace";
    }

    @ResponseBody
    @GetMapping("/api/local")
    public ResponseEntity<String> getLocalData(@RequestParam String query) {
        System.out.println(":query-----" + query);
        String apiUrl = "https://openapi.naver.com/v1/search/local.json";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", "fOXZIuSkI3VE1KbyV4eB");
        headers.set("X-Naver-Client-Secret", "zWVq4Jou43");

        // 쿼리 파라미터를 포함한 URL 생성
        String url = apiUrl + "?query=" + query + " 맛집&display=10";

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return ResponseEntity.ok(response.getBody());
    }
}
