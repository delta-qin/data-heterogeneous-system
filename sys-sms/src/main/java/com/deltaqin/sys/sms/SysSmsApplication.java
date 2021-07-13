package com.deltaqin.sys.sms;

import com.deltaqin.sys.common.annotation.EnableSysServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author deltaqin
 * @date 2020/9/13 6:30 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSysServerProtect
public class SysSmsApplication {
    public static void main(String[] args) {
        // at java.lang.IllegalStateException: Context has been already given a name
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(SysSmsApplication.class, args);
    }
}
