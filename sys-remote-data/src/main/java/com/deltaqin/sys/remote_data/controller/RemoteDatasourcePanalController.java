package com.deltaqin.sys.remote_data.controller;

import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.model.SysRemoteDatabasePanal;
import com.deltaqin.sys.remote_data.service.FeignPrestoService;
import com.deltaqin.sys.remote_data.service.RemoteDatasourcePanalService;
import com.deltaqin.sys.remote_data.service.RemoteDatasourceService;
import com.deltaqin.sys.remote_data.service.RemoteDatasourceTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RemoteDatasourcePanalController {
    @Autowired
    private FeignPrestoService feignPrestoService;

    @Autowired
    private RemoteDatasourceService remoteDatasourceService;

    @Autowired
    private RemoteDatasourceTypeService remoteDatasourceTypeService;

    @Autowired
    private RemoteDatasourcePanalService remoteDatasourcePanalService;

    @Autowired(required = false)
    private HttpServletRequest httpServletRequest;

    @ApiOperation("增加一个监控项")
    @PostMapping("/remoteDsPanal/signInPanal")
    public CommonResult signInPanal(@RequestBody SysRemoteDatabasePanal sysRemoteDatabasePanal) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        sysRemoteDatabasePanal.setCid(cid);

        remoteDatasourcePanalService.signInPanal(sysRemoteDatabasePanal);
        return CommonResult.success(sysRemoteDatabasePanal);
    }

    @ApiOperation("根据id删除一个监控项")
    @DeleteMapping("/remoteDsPanal/deletePanal")
    public CommonResult deletePanal(Long id) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        remoteDatasourcePanalService.deleteByID(id,cid);
        return CommonResult.success(null);
    }

    @ApiOperation("根据id修改一个监控项")
    @PutMapping("/remoteDsPanal/updatePanal")
    public CommonResult updatePanal(@RequestBody SysRemoteDatabasePanal sysRemoteDatabasePanal) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        if(sysRemoteDatabasePanal.getCid() != cid){
            return CommonResult.failed();
        }

        remoteDatasourcePanalService.updataByID(sysRemoteDatabasePanal);
        return CommonResult.success(null);
    }

    @ApiOperation("根据数据源id获取所有监控面板")
    @GetMapping("/remoteDsPanal/getAllPanalByID")
    public CommonResult getAllPanalByID(Long db_id) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        if(remoteDatasourceService.getByID(db_id).getCid() != cid){
            return CommonResult.failed();
        }

        List<SysRemoteDatabasePanal> panals = remoteDatasourcePanalService.getPanalByDbID(db_id,cid);

        return getData(panals);
    }

    @ApiOperation("获取该公司全部首页面板信息")
    @GetMapping("/remoteDsPanal/getAllPanalOnHomePage")
    public CommonResult getAllPanalOnHomePage() {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        List<SysRemoteDatabasePanal> panals = remoteDatasourcePanalService.getAllPanalOnHomePage(cid);

        return getData(panals);
    }

    @ApiOperation("获取该公司全部面板信息")
    @GetMapping("/remoteDsPanal/getAllPanal")
    public CommonResult getAllPanal() {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        List<SysRemoteDatabasePanal> panals = remoteDatasourcePanalService.getAllPanal(cid);

        return getData(panals);
    }

    @ApiOperation("修改该面板显示状态")
    @PutMapping("/remoteDsPanal/changeState")
    public CommonResult changeState(Long id) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        if(remoteDatasourcePanalService.changeState(id, cid)){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }


    private CommonResult getData(List<SysRemoteDatabasePanal> panals){
        List<Map<String,Object>> results = new ArrayList<>();
        for(SysRemoteDatabasePanal panal:panals){
            CommonResult commonResult = feignPrestoService.exeSQL(panal.getSqlWords());
            Map<String,Object> map = (HashMap<String,Object>)commonResult.getData();
            map.put("panal",panal);
            results.add(map);
        }

        HashMap<String, Object> finalResults = new HashMap<>();
        finalResults.put("rows", results);
        finalResults.put("total", results.size());
        return CommonResult.success(finalResults);
    }
}
