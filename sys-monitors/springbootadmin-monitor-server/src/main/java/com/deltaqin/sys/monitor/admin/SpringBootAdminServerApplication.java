package com.deltaqin.sys.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author deltaqin
 * @date 2020/9/11 7:21 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class SpringBootAdminServerApplication {
    public static void main(String[] args) {
        // at java.lang.IllegalStateException: Context has been already given a name
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(SpringBootAdminServerApplication.class,args);
    }
}
