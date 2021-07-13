package com.deltaqin.sys.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MapperScan指定的接口编译之后变成对应的实现类，在两个模块，
 * com.deltaqin.sys.mapper指的是生成代码模块的
 * com.deltaqin.sys.user.dao指的是用户模块的
 * @author deltaqin
 * @date 2020/9/8 11:12 上午
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.deltaqin.sys.mapper","com.deltaqin.sys.user.dao"})
public class MyBatisConfig {
}
