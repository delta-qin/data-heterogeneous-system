package com.deltaqin.sys.user;

import com.deltaqin.sys.common.annotation.EnableSysServerProtect;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



/**
 * @author deltaqin
 * @date 2020/9/8 11:05 上午
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableSysServerProtect

public class SysUserApplication {
    public static void main(String[] args) {
        // at java.lang.IllegalStateException: Context has been already given a name
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(SysUserApplication.class, args);
    }
}
