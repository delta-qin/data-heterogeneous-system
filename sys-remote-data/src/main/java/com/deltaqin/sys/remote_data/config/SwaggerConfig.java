package com.deltaqin.sys.remote_data.config;

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
                .apiBasePackage("com.deltaqin.sys.remote_data.controller")
                .title("远程数据源操作微服务")
                .description("远程数据源操作微服务接口文档")
                .contactName("deltaqin")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}

