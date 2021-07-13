package com.deltaqin.sys.remote_data.service.impl;

import com.deltaqin.sys.mapper.SysAdminMapper;
import com.deltaqin.sys.mapper.SysRemoteDatabaseInfoMapper;
import com.deltaqin.sys.model.SysAdmin;
import com.deltaqin.sys.model.SysAdminExample;
import com.deltaqin.sys.model.SysRemoteDatabaseInfo;
import com.deltaqin.sys.model.SysRemoteDatabaseInfoExample;
import com.deltaqin.sys.remote_data.entity.BaseQueryTool;
import com.deltaqin.sys.remote_data.entity.DatasourceConfiguration;
import com.deltaqin.sys.remote_data.entity.JdbcConstants;
import com.deltaqin.sys.remote_data.entity.MongoDBQueryTool;
import com.deltaqin.sys.remote_data.service.RemoteDatasourceService;
import com.deltaqin.sys.remote_data.utils.QueryToolFactory;
import com.github.pagehelper.PageHelper;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class RemoteDatasourceServiceImpl implements RemoteDatasourceService {
    @Autowired
    private SysRemoteDatabaseInfoMapper remoteDataMapper;

    @Override
    public List<SysRemoteDatabaseInfo> getAllDatasource(Long cid, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SysRemoteDatabaseInfoExample sysRemoteDatabaseInfoExample = new SysRemoteDatabaseInfoExample();
        sysRemoteDatabaseInfoExample.createCriteria().andCidEqualTo(cid);
        List<SysRemoteDatabaseInfo> sysRemoteDatabaseInfos = remoteDataMapper.selectByExample(sysRemoteDatabaseInfoExample);
        return sysRemoteDatabaseInfos;
    }

    @Override
    public void insert(SysRemoteDatabaseInfo sysRemoteDatabaseInfo) {
       remoteDataMapper.insertSelective(sysRemoteDatabaseInfo);
    }

    @Override
    public void delByID(long id) {
        remoteDataMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void uptByID(SysRemoteDatabaseInfo sysRemoteDatabaseInfo) {
        remoteDataMapper.updateByPrimaryKeySelective(sysRemoteDatabaseInfo);
    }

    @Override
    public SysRemoteDatabaseInfo getByID(long id) {
        return remoteDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public HashMap<String, Object> getAllDatasourceByType(Long cid, Long type_id) {
        SysRemoteDatabaseInfoExample sysRemoteDatabaseInfoExample = new SysRemoteDatabaseInfoExample();
        sysRemoteDatabaseInfoExample.createCriteria().andCidEqualTo(cid).andTypeIdEqualTo(type_id);
        List<SysRemoteDatabaseInfo> sysRemoteDatabaseInfos = remoteDataMapper.selectByExample(sysRemoteDatabaseInfoExample);
        HashMap<String,Object> result = new HashMap();
        result.put("total",sysRemoteDatabaseInfos.size());
        result.put("rows",sysRemoteDatabaseInfos);
        return result;
    }

    @Override
    public Boolean dataSourceTest(DatasourceConfiguration datasourceConfiguration){
        System.out.println(datasourceConfiguration);

//        if(datasourceConfiguration.getTypeId() == JdbcConstants.MONGODB){
//            try{
//                // 连接到 mongodb 服务
//                MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//
//                // 连接到数据库
//                MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
//                System.out.println("Connect to database successfully");
//
//            }catch(Exception e){
//                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            }
//        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasourceConfiguration);
        if (queryTool == null) {
            return false;
        }
        return queryTool.dataSourceTest();
    }
}
