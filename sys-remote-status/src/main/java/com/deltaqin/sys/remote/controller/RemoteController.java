package com.deltaqin.sys.remote.controller;

import cn.hutool.core.collection.CollUtil;
import com.deltaqin.sys.common.entities.CommonResult;

import com.deltaqin.sys.mapper.SysRemoteInfluxInfoMapper;
import com.deltaqin.sys.model.SysRemoteInfluxInfo;
import com.deltaqin.sys.model.SysRemoteInfluxPanal;
import com.deltaqin.sys.remote.service.RemoteService;
import com.deltaqin.sys.remote.utils.InfluxDbUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author deltaqin
 * @date 2020/9/20 3:11 下午
 */
@RestController
@Slf4j
@RequestMapping("influx")
@Api("influxDB时序数据库相关操作")
public class RemoteController {

    @Autowired
    private RemoteService remoteService;


    @ApiOperation("测试数据库连通性")
    @PostMapping("/ping")
    public CommonResult ping(@RequestBody SysRemoteInfluxInfo sysRemoteInfluxInfo){
        InfluxDbUtil influxDbUtil = new InfluxDbUtil(sysRemoteInfluxInfo.getUsername(),
                sysRemoteInfluxInfo.getPassword(),
                sysRemoteInfluxInfo.getUrl(),
                sysRemoteInfluxInfo.getDbName());
        return influxDbUtil.ping()? CommonResult.success("可以连通"):CommonResult.failed("无法连通");
    }

    @ApiOperation("保存influx数据库的信息")
    @PostMapping("/saveinfo")
    public CommonResult saveInfo(@RequestBody SysRemoteInfluxInfo sysRemoteInfluxInfo){


        remoteService.saveInfo(sysRemoteInfluxInfo);
        return CommonResult.success("保存成功");
    }

    @ApiOperation("更新influx数据库的信息(让用户修改的时候要先获取此数据库的信息)")
    @PostMapping("/updateinfo")
    public CommonResult updateInfo(@RequestBody SysRemoteInfluxInfo sysRemoteInfluxInfo){
        int i = remoteService.updateInfo(sysRemoteInfluxInfo);

        return i == 1 ? CommonResult.success("更新成功") : CommonResult.failed();
    }

    @ApiOperation("让用户修改的时候先获取此数据库的信息")
    @GetMapping("/getinfo")
    public CommonResult<SysRemoteInfluxInfo> getInfo(@RequestParam Long id){
        SysRemoteInfluxInfo sysRemoteInfluxInfo = remoteService.getInstanceInfo(id);

        return sysRemoteInfluxInfo != null ? CommonResult.success(sysRemoteInfluxInfo) : CommonResult.failed();
    }

    @ApiOperation("删除influx数据库的信息")
    @GetMapping("/deleteinfo")
    public CommonResult deleteInfo(@RequestParam Long id){
        int i = remoteService.deleteInfo(id);
        return 1 == 1? CommonResult.success("删除成功"):CommonResult.failed();
    }

    @ApiOperation("获取已经保存的所有influx数据库的信息")
    @GetMapping("/all/info")
    public CommonResult<List<SysRemoteInfluxInfo>> getAllInfo(){
        List<SysRemoteInfluxInfo> list = remoteService.getAllInfo();
        return CommonResult.success(list);
    }

    @ApiOperation("根据数据库ID和SQL语句获取时序信息")
    @GetMapping("/search")
    public CommonResult<QueryResult.Result> intoDb(String SQLStatement, Long DBIndex) {
        QueryResult.Result searchResult = remoteService.getSearchResult(SQLStatement, DBIndex);
        System.out.println(searchResult);

        if (searchResult.getSeries() != null) {
            return CommonResult.success(searchResult);
        }
        return CommonResult.failed();
    }

    @ApiOperation("保存设计好的显示面板（保存数据库id以及对应的SQL语句以及面板名字）")
    @PostMapping("/save/panal")
    public CommonResult savePanal(@RequestBody SysRemoteInfluxPanal sysRemoteInfluxPanal) {
        int i = remoteService.saveSearchSqlStatement(sysRemoteInfluxPanal);

        if (i == 1) {
            return CommonResult.success("保存成功");
        }
        return CommonResult.failed();
    }

    @ApiOperation("（先执行：查询已经保存的某数据库的所有面板信息）根据数据库ID")
    @GetMapping("/search/all/preserved")
    public CommonResult<List<SysRemoteInfluxPanal>> allPresered(Long DBIndex) {
        List<SysRemoteInfluxPanal> list = remoteService.getSearchPreserved( DBIndex);

        if (CollUtil.isNotEmpty(list)) {
            return CommonResult.success(list);
        }
        return CommonResult.failed();
    }

    @ApiOperation("（后执行：查询已经保存的某个面板）根据面板的 ID")
    @GetMapping("/search/preserved")
    public CommonResult<QueryResult.Result> searchPanal(Long id) {
        QueryResult.Result searchResult = remoteService.getSearchPreservedResult(id);

        if (searchResult.getSeries() != null) {
            return CommonResult.success(searchResult);
        }
        return CommonResult.failed();
    }


    @ApiOperation("更新面板的信息(让用户修改的时候要先获取此数据库的信息)")
    @PutMapping("/update/panal/info")
    public CommonResult updatePanalInfo(@RequestBody SysRemoteInfluxPanal sysRemoteInfluxPanal){
        int i = remoteService.updatePanalInfo(sysRemoteInfluxPanal);

        return i == 1 ? CommonResult.success("更新成功") : CommonResult.failed();
    }

    @ApiOperation("删除面板")
    @DeleteMapping("/delete/panal/info")
    public CommonResult deletePanalInfo(@RequestParam Long id){
        int i = remoteService.deletePanalInfo(id);
        return 1 == 1? CommonResult.success("删除成功"):CommonResult.failed();
    }

    /**
     * utc时间转成local时间
     * @param utcTime
     * @return
     */
    public static Date utcToLocal(String utcTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;
        try {
            utcDate = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        Date locatlDate = null;
        String localTime = sdf.format(utcDate.getTime());
        try {
            locatlDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locatlDate;
    }
}
