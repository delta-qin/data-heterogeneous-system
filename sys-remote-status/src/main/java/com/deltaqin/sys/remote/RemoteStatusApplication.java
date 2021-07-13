package com.deltaqin.sys.remote;

import com.deltaqin.sys.common.annotation.EnableSysServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author deltaqin
 * @date 2020/9/20 3:18 下午
 */
@SpringBootApplication
@EnableSysServerProtect
@EnableScheduling
public class RemoteStatusApplication {
    public static void main(String[] args) {

        // logback名字定义之后不能修改，nacos定义了。自己定义就冲突了，禁用nacos
        // at java.lang.IllegalStateException: Context has been already given a name
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(RemoteStatusApplication.class, args);
    }
}
