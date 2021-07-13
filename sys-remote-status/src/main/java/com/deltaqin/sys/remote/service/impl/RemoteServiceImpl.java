package com.deltaqin.sys.remote.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.deltaqin.sys.mapper.SysRemoteInfluxInfoMapper;
import com.deltaqin.sys.mapper.SysRemoteInfluxPanalMapper;
import com.deltaqin.sys.model.SysRemoteInfluxInfo;
import com.deltaqin.sys.model.SysRemoteInfluxInfoExample;
import com.deltaqin.sys.model.SysRemoteInfluxPanal;
import com.deltaqin.sys.model.SysRemoteInfluxPanalExample;
import com.deltaqin.sys.remote.service.RemoteService;
import com.deltaqin.sys.remote.utils.InfluxDbUtil;
import org.influxdb.dto.QueryResult;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.deltaqin.sys.remote.controller.RemoteController.utcToLocal;

/**
 * @author deltaqin
 * @date 2020/9/21 10:10 上午
 */
@Service
public class RemoteServiceImpl implements RemoteService {
    @Autowired(required = false)
    private SysRemoteInfluxInfoMapper sysRemoteInfluxInfoMapper;

    @Autowired(required = false)
    private SysRemoteInfluxPanalMapper sysRemoteInfluxPanalMapper;
    private Long id;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void saveInfo(SysRemoteInfluxInfo sysRemoteInfluxInfo) {
        sysRemoteInfluxInfo.setCid(Long.valueOf(request.getHeader("cid")));

        //根据ip进行位置信息搜索
        String url = sysRemoteInfluxInfo.getUrl();
        String ip = url.substring(url.indexOf("://") +3);
        ip = ip.substring(0,ip.indexOf(":"));
        System.out.println(ip);

        DbConfig config = null;
        try {
            config = new DbConfig();
            //获取ip库的位置（放在src下）（直接通过测试类获取文件Ip2RegionTest为测试类）
            String dbfile = RemoteServiceImpl.class.getResource("/ip2region/ip2region.db").getPath();
            DbSearcher searcher = new DbSearcher(config, dbfile);
            //采用Btree搜索
            DataBlock block = searcher.btreeSearch(ip);
            //打印位置信息（格式：国家|大区|省份|城市|运营商）
            String region = block.getRegion();
            StringBuilder group = new StringBuilder();
            String[] split = region.split("\\|");
//        System.out.println(split.toString());
            for (int i=0; i < split.length; i++){
//            System.out.println(split[i]);
                if (i ==2 | i==3) group.append(split[i]+"|");
            }
            String address = String.valueOf(group).substring(0,String.valueOf(group).indexOf("|"));
            if(address == "0"||address == ""||address == null){
                sysRemoteInfluxInfo.setAddress("未知ip");
            }else{
                sysRemoteInfluxInfo.setAddress(String.valueOf(group).substring(0,String.valueOf(group).indexOf("|")));
            }
        } catch (DbMakerConfigException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        sysRemoteInfluxInfoMapper.insertSelective(sysRemoteInfluxInfo);
    }

    @Override
    public QueryResult.Result getSearchResult(String sqlStatement, Long dbIndex) {
        SysRemoteInfluxInfo sysRemoteInfluxInfo = sysRemoteInfluxInfoMapper.selectByPrimaryKey(dbIndex);
        System.out.println(sysRemoteInfluxInfo);
        InfluxDbUtil influxDbUtil = new InfluxDbUtil(sysRemoteInfluxInfo.getUsername(),
                sysRemoteInfluxInfo.getPassword(),
                sysRemoteInfluxInfo.getUrl(),
                sysRemoteInfluxInfo.getDbName());
//        String querystat = "SELECT mean("usage_idle") FROM "cpu" WHERE ("cpu" = 'cpu-total')";
//        String querystat = "SELECT * FROM \"cpu\"";
        QueryResult results = influxDbUtil.query(sqlStatement);
        //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
        QueryResult.Result oneResult = results.getResults().get(0);
        return oneResult;
//        if (oneResult.getSeries() != null) {
//            List<List<Object>> objects = oneResult.getSeries().get(0).getValues();
//            objects.forEach(o -> {
//                List o1 =  o;
//                String s = (String) o1.get(0);
//                String date = utcToLocal(s).toString();
//                o1.set(0,date);
//            });
//            return oneResult;
//        }
//        return null;
    }

    @Override
    public QueryResult.Result getSearchPreservedResult(Long id) {
        System.out.println(id);
        SysRemoteInfluxPanal sysRemoteInfluxPanal = sysRemoteInfluxPanalMapper.selectByPrimaryKey(id);
        if (sysRemoteInfluxPanal != null){
            QueryResult.Result searchResult = getSearchResult(sysRemoteInfluxPanal.getSqlStatement(), sysRemoteInfluxPanal.getDbId());
            return searchResult;
        }
        return null;
    }

    @Override
    public int saveSearchSqlStatement(SysRemoteInfluxPanal sysRemoteInfluxPanal) {
        sysRemoteInfluxPanal.setCid(Long.valueOf(request.getHeader("cid")));
        int i = sysRemoteInfluxPanalMapper.insertSelective(sysRemoteInfluxPanal);
        return i;
    }

    @Override
    public List<SysRemoteInfluxPanal> getSearchPreserved(Long dbIndex) {
        SysRemoteInfluxPanalExample sysRemoteInfluxPanalExample = new SysRemoteInfluxPanalExample();
        sysRemoteInfluxPanalExample.createCriteria()
                .andDbIdEqualTo(dbIndex)
                .andCidEqualTo(Long.valueOf(request.getHeader("cid")));
        List<SysRemoteInfluxPanal> sysRemoteInfluxPanals = sysRemoteInfluxPanalMapper.selectByExample(sysRemoteInfluxPanalExample);
        if (CollUtil.isNotEmpty(sysRemoteInfluxPanals)){
            return sysRemoteInfluxPanals;
        }
        return null;

    }

    @Override
    public List<SysRemoteInfluxInfo> getAllInfo() {
        SysRemoteInfluxInfoExample sysRemoteInfluxInfoExample = new SysRemoteInfluxInfoExample();
        sysRemoteInfluxInfoExample.createCriteria().andCidEqualTo(Long.valueOf(request.getHeader("cid")));
        return sysRemoteInfluxInfoMapper.selectByExample(sysRemoteInfluxInfoExample);
    }

    @Override
    public int updateInfo(SysRemoteInfluxInfo sysRemoteInfluxInfo) {
        return sysRemoteInfluxInfoMapper.updateByPrimaryKeySelective(sysRemoteInfluxInfo);
    }

    @Override
    public SysRemoteInfluxInfo getInstanceInfo(Long id) {

        SysRemoteInfluxInfoExample sysRemoteInfluxInfoExample = new SysRemoteInfluxInfoExample();
        sysRemoteInfluxInfoExample.createCriteria()
                .andIdEqualTo(id)
                .andCidEqualTo(Long.valueOf(request.getHeader("cid")));
        List<SysRemoteInfluxInfo> sysRemoteInfluxInfos = sysRemoteInfluxInfoMapper.selectByExample(sysRemoteInfluxInfoExample);
        if (CollUtil.isNotEmpty(sysRemoteInfluxInfos))
            return sysRemoteInfluxInfos.get(0);
        return null;
    }

    @Override
    public int deleteInfo(Long id) {
        SysRemoteInfluxInfoExample sysRemoteInfluxInfoExample = new SysRemoteInfluxInfoExample();
        sysRemoteInfluxInfoExample.createCriteria()
                .andIdEqualTo(id)
                .andCidEqualTo(Long.valueOf(request.getHeader("cid")));

        return sysRemoteInfluxInfoMapper.deleteByExample(sysRemoteInfluxInfoExample);
    }

    @Override
    public int updatePanalInfo(SysRemoteInfluxPanal sysRemoteInfluxPanal) {

        return sysRemoteInfluxPanalMapper.updateByPrimaryKeySelective(sysRemoteInfluxPanal);
    }

    @Override
    public int deletePanalInfo(Long id) {
        SysRemoteInfluxPanalExample sysRemoteInfluxPanalExample = new SysRemoteInfluxPanalExample();
        sysRemoteInfluxPanalExample.createCriteria()
                .andCidEqualTo(Long.valueOf(request.getHeader("cid")))
                .andIdEqualTo(id);

        return sysRemoteInfluxPanalMapper.deleteByExample(sysRemoteInfluxPanalExample);
    }
}
