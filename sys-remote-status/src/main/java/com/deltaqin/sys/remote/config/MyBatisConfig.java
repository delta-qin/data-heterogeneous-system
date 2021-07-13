package com.deltaqin.sys.remote.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.deltaqin.sys.mapper"})
public class MyBatisConfig {
}
