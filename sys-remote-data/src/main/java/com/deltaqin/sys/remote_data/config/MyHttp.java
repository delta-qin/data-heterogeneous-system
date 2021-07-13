package com.deltaqin.sys.remote_data.config;

import com.deltaqin.sys.common.entities.CommonResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class MyHttp {
    private static RestTemplate restTemplate = new RestTemplate();

    public static CommonResult PUT(String url,MultiValueMap<String, Object> paramMap){
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("api-version", "1.0");

        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(paramMap, requestHeaders);

        //PUT
        ResponseEntity<CommonResult> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, CommonResult.class);
        CommonResult commonResult = response.getBody();
        return commonResult;
    }

    public static CommonResult POST(String url,MultiValueMap<String, Object> paramMap){
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("api-version", "1.0");

        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(paramMap, requestHeaders);

        //POST
        ResponseEntity<CommonResult> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, CommonResult.class);
        CommonResult commonResult = response.getBody();
        return commonResult;
    }

    public static CommonResult DELETE(String url,MultiValueMap<String, Object> paramMap){
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("api-version", "1.0");

        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(paramMap, requestHeaders);

        //DELETE
        ResponseEntity<CommonResult> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, CommonResult.class);
        CommonResult commonResult = response.getBody();
        return commonResult;
    }
}
