package com.deltaqin.sys.presto.config;//package com.deltaqin.sys.remote_data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class GlobalDataSourceConfiguration {
    @Bean(name = "prestoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.presto")
    public DataSource prestoDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

    @Autowired
    @Qualifier("prestoDataSource")
    DataSource dataSource ;

    @Bean(name = "prestoTemplate")
    public JdbcTemplate prestoJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}
