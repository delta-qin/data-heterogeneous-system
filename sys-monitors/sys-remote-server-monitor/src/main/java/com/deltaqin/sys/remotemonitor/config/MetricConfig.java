package com.deltaqin.sys.remotemonitor.config;

import com.codahale.metrics.*;
import metrics_influxdb.InfluxdbReporter;
import metrics_influxdb.api.protocols.InfluxdbProtocols;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author deltaqin
 * @date 2020/9/20 12:12 上午
 */
@Configuration
public class MetricConfig {

    // 注册metric的容器MetricRegistry,这是Metrics的核心
    @Bean
    public MetricRegistry MetricRegistrymetrics() {
        return new MetricRegistry();
    }

    // 数据收集到influxdb
    @Bean(name ="influxdbReporter")
//    @Qualifier
    public com.codahale.metrics.ScheduledReporter ScheduledReporterinfluxdbReporter(MetricRegistry metrics){
        return InfluxdbReporter.forRegistry(metrics)
                .protocol(InfluxdbProtocols.http("106.52.185.195",8086,"deltaqin","deltaqin","database"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MICROSECONDS)
                .filter(MetricFilter.ALL)
                .skipIdleMetrics(false)
                .build();
    }

    // 后台Console输出数据
    @Bean
    public ConsoleReporter ConsoleReporterconsoleReporter(MetricRegistry metrics) {
        return ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MICROSECONDS)
                .filter(MetricFilter.ALL)
                .build();
    }

    // Meters TPS计算器
    @Bean
    public com.codahale.metrics.Meter MeterrequestMeter(MetricRegistry metrics) {
        return metrics.meter("request");
    }

    // 计时器
    @Bean
    public com.codahale.metrics.Timer Timerresponses(MetricRegistry metrics) {
        return metrics.timer("executeTime");
    }

    // Counter计数器,可以用来统计队列中Job的总数。
    @Bean
    public com.codahale.metrics.Counter CounterpendingJobs(MetricRegistry metrics) {
        return metrics.counter("requestCount");
    }

    //Histogram 直方图数据
    @Bean
    public com.codahale.metrics.Histogram HistogramresponseSizes(MetricRegistry metrics) {
        return metrics.histogram("Histogram-sizes");
    }

    @Bean
    public Slf4jReporter Slf4jReporterslf4jReporter(MetricRegistry metrics) {
        return Slf4jReporter.forRegistry(metrics)
                .outputTo(LoggerFactory.getLogger("Slf4jMetrics"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean
    public JmxReporter JmxReporterjmxReporter(MetricRegistry metrics) {
        return JmxReporter.forRegistry(metrics).build();
    }

    // Gauge 衡量一个待处理队列中任务的个数
    public class QueueManager{
        public Queue<String> q = new LinkedList<>();
        public QueueManager(MetricRegistry metricRegistry, String name){
            metricRegistry.register(MetricRegistry.name(QueueManager.class, "cache","size"),
                    (Gauge<Integer>) () -> {
                        return q.size(); //衡量一个待处理队列中任务的个数
                    });
        }
    }
}
