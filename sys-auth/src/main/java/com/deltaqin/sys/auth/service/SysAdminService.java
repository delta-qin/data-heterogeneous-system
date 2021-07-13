package com.deltaqin.sys.auth.service;

import com.deltaqin.sys.common.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微服务调用，获取用户信息
 * @author deltaqin
 * @date 2020/9/8 8:25 上午
 */
//只需要声明一个带有@FeignClient注解的接口，就声明好了一个Feign的http请求接口
@FeignClient("sys-user")
public interface SysAdminService {

    @GetMapping("/admin/loadByUsername")
    User loadUserByUsername(@RequestParam String username);

}
