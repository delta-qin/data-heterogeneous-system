package com.deltaqin.sys.presto;

import com.deltaqin.sys.common.annotation.EnableSysGatewayFeignClient;
import com.deltaqin.sys.common.annotation.EnableSysServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author deltaqin
 * @date 2020/9/29 8:19 下午
 */
@EnableSysServerProtect
@EnableSysGatewayFeignClient
@SpringBootApplication
public class PrestoApplication {
    public static void main(String[] args) {
        // logback名字定义之后不能修改，nacos定义了。自己定义就冲突了，禁用nacos
        // at java.lang.IllegalStateException: Context has been already given a name
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(PrestoApplication.class,args);
    }
}
