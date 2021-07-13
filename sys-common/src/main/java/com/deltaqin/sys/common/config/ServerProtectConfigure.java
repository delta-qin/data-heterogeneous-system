package com.deltaqin.sys.common.config;

import com.deltaqin.sys.common.interceptor.SysServerProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置网关token验证拦截器（微服务防护）
 * @author deltaqin
 * @date 2020/9/13 4:08 下午
 */

public class ServerProtectConfigure implements WebMvcConfigurer {
    // 注册拦截器，加入到容器中，
    @Bean
    public HandlerInterceptor sysServerProtectInterceptor() {
        return new SysServerProtectInterceptor();
    }

    // 加入到拦截器链
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sysServerProtectInterceptor());
    }
}
