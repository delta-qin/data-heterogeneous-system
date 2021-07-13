package com.deltaqin.sys.remotemonitor.controller;

import com.codahale.metrics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author deltaqin
 * @date 2020/9/20 9:00 上午
 */
@Controller
@RequestMapping("/")
public class MonitorController {
    @Autowired
    private Meter requestMeter;

    @Autowired
    private Timer responses;

    @Autowired
    private ScheduledReporter influxdbReporter;

    @Autowired
    private Counter pendingJobs;

    @Autowired
    private Histogram responseSizes;

    @Autowired
    private JmxReporter jmxReporter;


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        requestMeter.mark(); //Meters TPS计算器调用mark()方法，来增加计数
        final Timer.Context context = responses.time(); //Timer计时器统计信息
        pendingJobs.inc(); //counter调用inc()增加计数
        pendingJobs.dec(); //counter调用dec()减去计数
        responseSizes.update(new Random().nextInt(10));// 直方图调用Histogram的update()
        jmxReporter.start();//JMX
        try {
            return "Hello Metrics";
        } finally {
            context.stop();
            influxdbReporter.start(5, TimeUnit.SECONDS); //每5秒钟往数据库添加监控数据
        }
    }
}
