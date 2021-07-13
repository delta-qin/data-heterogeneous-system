package com.deltaqin.sys.remote_data.entity;

import java.sql.SQLException;

public class MySQLQueryTool extends BaseQueryTool {

    public MySQLQueryTool(DatasourceConfiguration jobDatasource) throws SQLException {
        super(jobDatasource);
    }

}
