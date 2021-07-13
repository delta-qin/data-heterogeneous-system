package com.deltaqin.sys.remote_data.entity;

import java.sql.SQLException;

public class SqlServerQueryTool extends BaseQueryTool {
    public SqlServerQueryTool(DatasourceConfiguration jobDatasource) throws SQLException {
        super(jobDatasource);
    }
}
