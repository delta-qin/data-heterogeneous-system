package com.deltaqin.sys.auth.config;

import com.deltaqin.sys.common.config.BaseSwaggerConfig;
import com.deltaqin.sys.common.entities.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 文档配置类
 * @author deltaqin
 * @date 2020/9/8 12:37 上午
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.deltaqin.sys.auth.controller")
                .title("认证中心")
                .description("认证中心相关接口文档")
                .contactName("deltaqin")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
