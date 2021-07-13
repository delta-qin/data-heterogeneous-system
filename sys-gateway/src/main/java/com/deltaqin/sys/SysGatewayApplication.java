package com.deltaqin.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author deltaqin
 * @date 2020/9/6 4:05 下午
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SysGatewayApplication {
    public static void main(String[] args) {
        // at java.lang.IllegalStateException: Context has been already given a name
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(SysGatewayApplication.class, args);
    }
}
