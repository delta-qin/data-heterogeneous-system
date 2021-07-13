package com.deltaqin.sys.remote_data;

import com.deltaqin.sys.common.annotation.EnableSysGatewayFeignClient;
import com.deltaqin.sys.common.annotation.EnableSysServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableSysServerProtect
@EnableFeignClients
@EnableSysServerProtect
@EnableSysGatewayFeignClient
public class SysRemoteDatasourceApplication {
    public static void main(String[] args) {
        // logback名字定义之后不能修改，nacos定义了。自己定义就冲突了，禁用nacos
        // at java.lang.IllegalStateException: Context has been already given a name
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(SysRemoteDatasourceApplication.class, args);
    }
}
