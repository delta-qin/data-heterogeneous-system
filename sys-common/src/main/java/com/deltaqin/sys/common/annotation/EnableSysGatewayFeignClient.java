package com.deltaqin.sys.common.annotation;

import com.deltaqin.sys.common.config.SysGateWayFeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义注解，
 * 使用feign的时候在头部加上网关校验，否则不通过微服务拦截器
 * @author deltaqin
 * @date 2020/9/13 4:27 下午
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SysGateWayFeignConfigure.class)
public @interface EnableSysGatewayFeignClient {
}
