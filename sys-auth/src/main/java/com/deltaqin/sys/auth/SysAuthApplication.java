package com.deltaqin.sys.auth;

import com.deltaqin.sys.common.annotation.EnableSysGatewayFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 * @author deltaqin
 * @date 2020/9/6 5:05 下午
 */
@EnableDiscoveryClient
@EnableSysGatewayFeignClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.deltaqin.sys.auth")
public class SysAuthApplication {
    public static void main(String[] args) {
        // logback名字定义之后不能修改，nacos定义了。自己定义就冲突了，禁用nacos
        // at java.lang.IllegalStateException: Context has been already given a name
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(SysAuthApplication.class, args);
    }
}
