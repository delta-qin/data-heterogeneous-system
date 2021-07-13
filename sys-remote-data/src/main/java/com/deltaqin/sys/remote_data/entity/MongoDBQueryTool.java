package com.deltaqin.sys.remote_data.entity;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

import java.sql.SQLException;
import java.util.ArrayList;


public class MongoDBQueryTool extends BaseQueryTool{
    private static DatasourceConfiguration jobDatasource;

    public MongoDBQueryTool(DatasourceConfiguration jobDatasource) throws SQLException {
        super(jobDatasource);
        this.jobDatasource = jobDatasource;
    }
    /**
     * 测试是否连接成功
     *
     * @return
     */
    @Override
    public boolean dataSourceTest() {
        try{
            String urls = jobDatasource.getDbUrl();

            ArrayList<ServerAddress> address = new ArrayList<>();
            String[] split = urls.split(",");
            for(String s : split){
                String[] info = s.split(":");
                if(info.length!=2){
                    return false;
                }
                address.add(new ServerAddress(info[0], Integer.parseInt(info[1])));
            }

            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient(address);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }
    }
}
