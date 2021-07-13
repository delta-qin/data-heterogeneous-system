package com.deltaqin.sys.remotemonitor;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Slf4jReporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author deltaqin
 * @date 2020/9/20 9:07 上午
 */
@SpringBootApplication
public class RemoteServerApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RemoteServerApplication.class, args);
        ConsoleReporter reporter = ctx.getBean(ConsoleReporter.class);
        //5m一次后台输出
        reporter.start(5, TimeUnit.SECONDS);
        //slf4j日志
        Slf4jReporter slf4jReporter = ctx.getBean(Slf4jReporter.class);
        slf4jReporter.start(5,TimeUnit.SECONDS);

    }
}
