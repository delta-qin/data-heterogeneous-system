package com.deltaqin.sys.remote.config;

import com.deltaqin.sys.common.config.BaseSwaggerConfig;
import com.deltaqin.sys.common.entities.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.deltaqin.sys.remote.controller")
                .title("监控远程服务器微服务")
                .description("监控远程服务器相关接口文档")
                .contactName("deltaqin")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
