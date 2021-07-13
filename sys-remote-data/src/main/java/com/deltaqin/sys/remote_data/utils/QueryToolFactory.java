package com.deltaqin.sys.remote_data.utils;

import com.deltaqin.sys.remote_data.entity.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 工具类，获取单例实体
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName QueryToolFactory
 * @Version 1.0
 * @since 2019/7/18 9:36
 */
public class QueryToolFactory {

    public static BaseQueryTool getByDbType(DatasourceConfiguration jobDatasource) {
        //获取dbType
        int datasourceTypeId = jobDatasource.getTypeId();
        switch(datasourceTypeId){
            case JdbcConstants.MYSQL:
                return getMySQLQueryToolInstance(jobDatasource);
            case JdbcConstants.POSTGRESQL:
                return getPostgresqlQueryToolInstance(jobDatasource);
            case JdbcConstants.SQL_SERVER:
                return getSqlserverQueryToolInstance(jobDatasource);
            case JdbcConstants.MONGODB:
                return getMongodbQueryToolInstance(jobDatasource);
//            case JdbcConstants.ORACLE:
//                return getOracleQueryToolInstance(jobDatasource);
        }
        throw new UnsupportedOperationException("找不到该类型: "+ datasourceTypeId);
    }

    private static BaseQueryTool getMySQLQueryToolInstance(DatasourceConfiguration jdbcDatasource) {
        try {
            return new MySQLQueryTool(jdbcDatasource);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static BaseQueryTool getPostgresqlQueryToolInstance(DatasourceConfiguration jdbcDatasource) {
        try {
            return new PostgresqlQueryTool(jdbcDatasource);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static BaseQueryTool getSqlserverQueryToolInstance(DatasourceConfiguration jdbcDatasource) {
        try {
            return new SqlServerQueryTool(jdbcDatasource);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static BaseQueryTool getMongodbQueryToolInstance(DatasourceConfiguration jdbcDatasource) {
        try {
            return new MongoDBQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private static BaseQueryTool getOracleQueryToolInstance(DatasourceConfiguration jdbcDatasource) {
//        try {
//            return new OracleQueryTool(jdbcDatasource);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }
}
