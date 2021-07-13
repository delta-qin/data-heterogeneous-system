package com.deltaqin.sys.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 禁止直接访问集群状态
 * @author deltaqin
 * @date 2020/9/13 3:32 下午
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:sys-gateway.properties"})
@ConfigurationProperties(prefix = "sys.gateway")
public class SysGatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
