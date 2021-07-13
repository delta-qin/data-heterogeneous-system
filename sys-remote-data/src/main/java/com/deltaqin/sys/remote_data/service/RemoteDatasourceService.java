package com.deltaqin.sys.remote_data.service;

import com.deltaqin.sys.model.SysRemoteDatabaseInfo;
import com.deltaqin.sys.remote_data.entity.DatasourceConfiguration;

import java.util.HashMap;
import java.util.List;

public interface RemoteDatasourceService {
    List<SysRemoteDatabaseInfo> getAllDatasource(Long cid, Integer pageSize, Integer pageNum);

    void insert(SysRemoteDatabaseInfo sysRemoteDatabaseInfo);

    void delByID(long id);

    void uptByID(SysRemoteDatabaseInfo sysRemoteDatabaseInfo);

    SysRemoteDatabaseInfo getByID(long id);

    HashMap<String,Object> getAllDatasourceByType(Long cid,Long type_id);

    Boolean dataSourceTest(DatasourceConfiguration jobJdbcDatasource);
}
