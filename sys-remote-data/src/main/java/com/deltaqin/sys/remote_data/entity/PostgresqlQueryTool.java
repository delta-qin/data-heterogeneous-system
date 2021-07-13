
package com.deltaqin.sys.remote_data.entity;

import java.sql.SQLException;

public class PostgresqlQueryTool extends BaseQueryTool {
    public PostgresqlQueryTool(DatasourceConfiguration jobDatasource) throws SQLException {
        super(jobDatasource);
    }

}
