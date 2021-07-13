package com.deltaqin.sys.remote_data.entity;

import java.sql.SQLException;

public class OracleQueryTool extends BaseQueryTool {

    public OracleQueryTool(DatasourceConfiguration jobDatasource) throws SQLException {
        super(jobDatasource);
    }
}
