package com.deltaqin.sys.remote_data.service.impl;

import com.deltaqin.sys.mapper.SysRemoteDatabaseTypeMapper;
import com.deltaqin.sys.model.SysRemoteDatabaseType;
import com.deltaqin.sys.model.SysRemoteDatabaseTypeExample;
import com.deltaqin.sys.remote_data.service.RemoteDatasourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RemoteDataTypeServiceImpl implements RemoteDatasourceTypeService {
    @Autowired
    private SysRemoteDatabaseTypeMapper remoteDataTypeMapper;

    @Override
    public String getTypeByID(Long id) {

        return remoteDataTypeMapper.selectByPrimaryKey(id).getType();
    }

    @Override
    public HashMap<String, Object> getAllType() {
        SysRemoteDatabaseTypeExample sysRemoteDatabaseTypeExample = new SysRemoteDatabaseTypeExample();
        sysRemoteDatabaseTypeExample.createCriteria();
        List<SysRemoteDatabaseType> sysRemoteDatabaseTypes = remoteDataTypeMapper.selectByExample(sysRemoteDatabaseTypeExample);

        HashMap<String, Object> result = new HashMap<>();
        result.put("total",sysRemoteDatabaseTypes.size());
        result.put("rows",sysRemoteDatabaseTypes);
        return result;
    }
}
