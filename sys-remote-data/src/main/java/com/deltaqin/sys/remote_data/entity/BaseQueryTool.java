package com.deltaqin.sys.remote_data.entity;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

/**
 * 抽象查询工具
 */
public abstract class BaseQueryTool  {

    protected static final Logger logger = LoggerFactory.getLogger(BaseQueryTool.class);

    private DataSource datasource;

    private Connection connection;

    /**
     * 构造方法
     */
    BaseQueryTool(DatasourceConfiguration jobDatasource) throws SQLException {
        getDataSource(jobDatasource);
    }

    private void getDataSource(DatasourceConfiguration jobDatasource) throws SQLException {
        int datasourceID = jobDatasource.getTypeId();
        String driver = "";
        String url = "";
        switch(datasourceID){
            case JdbcConstants.MYSQL:
                driver = JdbcConstants.MYSQL_DRIVER;
                url = "jdbc:mysql://" + jobDatasource.getDbUrl();
                break;
            case JdbcConstants.POSTGRESQL:
                driver = JdbcConstants.POSTGRESQL_DRIVER;
                url = "jdbc:postgresql://" + jobDatasource.getDbUrl();
                break;
            case JdbcConstants.SQL_SERVER:
                driver = JdbcConstants.SQL_SERVER_DRIVER;
                url = "jdbc:sqlserver://" + jobDatasource.getDbUrl();
                break;
            case JdbcConstants.MONGODB:
                return;
//            case JdbcConstants.ORACLE:
//                driver = JdbcConstants.ORACLE_DRIVER;
//                url = "jdbc:oracle:thin:@//" + jobDatasource.getDbUrl();
//                break;
        }
        //这里默认使用 hikari 数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(jobDatasource.getDbUser());
        dataSource.setPassword(jobDatasource.getDbPassword());
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driver);
        this.datasource = dataSource;
        this.connection = this.datasource.getConnection();
    }

    public boolean dataSourceTest() {
        boolean result = false;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            if (metaData.getDatabaseProductName().length() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            logger.error("[dataSourceTest Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
