package com.deltaqin.sys.remote.service;

import com.deltaqin.sys.model.SysRemoteInfluxInfo;
import com.deltaqin.sys.model.SysRemoteInfluxPanal;
import org.influxdb.dto.QueryResult;

import java.util.List;

/**
 * @author deltaqin
 * @date 2020/9/20 8:16 下午
 */
public interface RemoteService {

    void saveInfo(SysRemoteInfluxInfo sysRemoteInfluxInfo);

    QueryResult.Result getSearchResult(String sqlStatement, Long dbIndex);

    QueryResult.Result getSearchPreservedResult(Long Id);

    int saveSearchSqlStatement(SysRemoteInfluxPanal sysRemoteInfluxPanal);

    List<SysRemoteInfluxPanal> getSearchPreserved(Long dbIndex);

    List<SysRemoteInfluxInfo> getAllInfo();

    int updateInfo(SysRemoteInfluxInfo sysRemoteInfluxInfo);

    SysRemoteInfluxInfo getInstanceInfo(Long id);

    int deleteInfo(Long id);

    int updatePanalInfo(SysRemoteInfluxPanal sysRemoteInfluxPanal);

    int deletePanalInfo(Long id);
}
