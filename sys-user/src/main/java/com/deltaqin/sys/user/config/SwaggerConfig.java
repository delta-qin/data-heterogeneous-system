package com.deltaqin.sys.user.config;

import com.deltaqin.sys.common.config.BaseSwaggerConfig;
import com.deltaqin.sys.common.entities.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author deltaqin
 * @date 2020/9/8 11:07 上午
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.deltaqin.sys.user.controller")
                .title("用户登录微服务")
                .description("用户登录微服务接口文档")
                .contactName("deltaqin")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}

