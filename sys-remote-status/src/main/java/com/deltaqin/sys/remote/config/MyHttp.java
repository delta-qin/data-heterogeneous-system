package com.deltaqin.sys.remote.config;

import com.alibaba.fastjson.JSONObject;
import com.deltaqin.sys.common.entities.CommonResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

public class MyHttp {
    private static RestTemplate restTemplate = new RestTemplate();

    public static CommonResult POST(String url, JSONObject paramMap) {
        ResponseEntity<CommonResult> response = restTemplate.postForEntity(url, paramMap, CommonResult.class);
        CommonResult commonResult = response.getBody();
        return commonResult;
    }
}
