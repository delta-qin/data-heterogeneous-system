package com.deltaqin.sys.common.config;

import com.deltaqin.sys.common.constant.AuthConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

/**
 * 使用feign的时候需要在头部加上网关校验，否则不通过拦截器
 * @author deltaqin
 * @date 2020/9/13 4:23 下午
 */
//@Configuration
public class SysGateWayFeignConfigure {
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        // 接口实现，lamda写法，自定请求拦截器需要实现 feign.RequestInterceptor 函数式接口
        // 该接口的方法 apply 有参数 template ，该参数类型为 RequestTemplate
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(AuthConstant.GATEWAY_SECRET.getBytes()));
            requestTemplate.header(AuthConstant.GATEWAY_HEADER, gatewayToken);
        };
    }
}
