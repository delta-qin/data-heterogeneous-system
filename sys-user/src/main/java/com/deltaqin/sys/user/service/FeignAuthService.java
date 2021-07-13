package com.deltaqin.sys.user.service;

import com.deltaqin.sys.common.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author deltaqin
 * @date 2020/9/8 6:40 下午
 */
@FeignClient("sys-auth")
public interface FeignAuthService {

    @PostMapping(value = "/oauth/token")
    CommonResult getAccessToken(@RequestParam Map<String, String> parameters);

}
