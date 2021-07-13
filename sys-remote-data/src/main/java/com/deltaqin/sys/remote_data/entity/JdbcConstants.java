package com.deltaqin.sys.remote_data.entity;


public interface JdbcConstants {
    //INSERT INTO `heterogeneous2`.`sys_remote_database_type`(`id`, `type`) VALUES (1, 'mysql');
    //INSERT INTO `heterogeneous2`.`sys_remote_database_type`(`id`, `type`) VALUES (2, 'oracle');
    //INSERT INTO `heterogeneous2`.`sys_remote_database_type`(`id`, `type`) VALUES (3, 'postgresql');
    //INSERT INTO `heterogeneous2`.`sys_remote_database_type`(`id`, `type`) VALUES (4, 'sqlserver');
    //INSERT INTO `heterogeneous2`.`sys_remote_database_type`(`id`, `type`) VALUES (5, 'hive');
    //INSERT INTO `heterogeneous2`.`sys_remote_database_type`(`id`, `type`) VALUES (6, 'hbase');
    //INSERT INTO `heterogeneous2`.`sys_remote_database_type`(`id`, `type`) VALUES (7, 'mongodb');

    int MYSQL                      = 1;
    String MYSQL_DRIVER               = "com.mysql.jdbc.Driver";

    int POSTGRESQL                 = 2;
    String POSTGRESQL_DRIVER          = "org.postgresql.Driver";

    int SQL_SERVER                 = 3;
    String SQL_SERVER_DRIVER          = "com.microsoft.jdbc.sqlserver.SQLServerDriver";

    int MONGODB                    = 4;



//    int ORACLE                     = 5;
//    String ORACLE_DRIVER              = "oracle.jdbc.OracleDriver";
}
