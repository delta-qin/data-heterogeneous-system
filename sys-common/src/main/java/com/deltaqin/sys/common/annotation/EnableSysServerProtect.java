package com.deltaqin.sys.common.annotation;

import com.deltaqin.sys.common.config.ServerProtectConfigure;
import com.deltaqin.sys.common.interceptor.SysServerProtectInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 开启微服务网关验证，携带网关token的请求才可以通过
 * @author deltaqin
 * @date 2020/9/13 4:14 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerProtectConfigure.class)
public @interface EnableSysServerProtect {
}
