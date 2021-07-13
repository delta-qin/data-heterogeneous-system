package com.deltaqin.sys.remote_data.controller;

import com.deltaqin.sys.common.constant.CodeConstant;
import com.deltaqin.sys.common.entities.CommonPage;
import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.model.SysRemoteDatabaseInfo;
import com.deltaqin.sys.remote_data.config.MyHttp;
import com.deltaqin.sys.remote_data.config.PrestoConfig;
import com.deltaqin.sys.remote_data.entity.DatasourceConfiguration;
import com.deltaqin.sys.remote_data.service.FeignPrestoService;
import com.deltaqin.sys.remote_data.service.RemoteDatasourcePanalService;
import com.deltaqin.sys.remote_data.service.RemoteDatasourceService;
import com.deltaqin.sys.remote_data.service.RemoteDatasourceTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RemoteDatasourceInfoController {
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

    @PostMapping("/test")
    @ApiOperation("测试数据")
    public CommonResult dataSourceTest (@RequestBody DatasourceConfiguration jobJdbcDatasource) throws IOException {
        if (!remoteDatasourceService.dataSourceTest(jobJdbcDatasource)){
            return CommonResult.failed();
        }
        return CommonResult.success("success");
    }

    @ApiOperation("查看所有数据源")
    @GetMapping("/remoteDatasource/getInfo")
    public CommonResult getInfo(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
//        Long cid = Long.parseLong((String) httpServletRequest.getAttribute("cid"));//获取公司id
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        List<SysRemoteDatabaseInfo> allDatasource = remoteDatasourceService.getAllDatasource(cid, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(allDatasource));
    }

    @ApiOperation("根据类型查看所有数据源")
    @GetMapping("/remoteDatasource/getInfoByType")
    public CommonResult getInfoByType(Long type_id) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        return CommonResult.success(remoteDatasourceService.getAllDatasourceByType(cid,type_id));
    }

    @ApiOperation("查看支持的所有数据源类型")
    @GetMapping("/remoteDatasourceType/getInfo")
    public CommonResult getAllType() {
        return CommonResult.success(remoteDatasourceTypeService.getAllType());
    }

    @ApiOperation("新增一个数据源")
    @PostMapping("/remoteDatasource/singIn")
    public CommonResult singIn(@RequestBody SysRemoteDatabaseInfo sysRemoteDatabaseInfo) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        sysRemoteDatabaseInfo.setCid(cid);
        remoteDatasourceService.insert(sysRemoteDatabaseInfo);

        //body
        MultiValueMap<String, Object> paramMap = encap(sysRemoteDatabaseInfo.getId(), sysRemoteDatabaseInfo.getTypeId());
        paramMap.add("db_url", sysRemoteDatabaseInfo.getDbUrl());
        paramMap.add("db_user", sysRemoteDatabaseInfo.getDbUser());
        paramMap.add("db_password", sysRemoteDatabaseInfo.getDbPassword());

        //POST
        CommonResult commonResult = MyHttp.POST(PrestoConfig.addUrl(), paramMap);

        if (commonResult.getCode() == CodeConstant.SUCCESS.getCode()) {
            return CommonResult.success(sysRemoteDatabaseInfo);
        } else {
            remoteDatasourceService.delByID(sysRemoteDatabaseInfo.getId());
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID删除某个数据源")
    @DeleteMapping("/remoteDatasource/deleteInfo")
    public CommonResult<String> delete(Long id) {
        SysRemoteDatabaseInfo sysRemoteDatabaseInfo = remoteDatasourceService.getByID(id);

        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        if(!cid.equals(sysRemoteDatabaseInfo.getCid())){
            return CommonResult.failed();
        }
        //body
        MultiValueMap<String, Object> paramMap = encap(sysRemoteDatabaseInfo.getId(), sysRemoteDatabaseInfo.getTypeId());
        remoteDatasourceService.delByID(id);
        return CommonResult.success(null);
        //DELETE
//        CommonResult commonResult = MyHttp.DELETE(PrestoConfig.delUrl(), paramMap);
//
//        if (commonResult.getCode() == CodeConstant.SUCCESS.getCode()) {
//            remoteDatasourceService.delByID(id);
//            return CommonResult.success(null);
//        } else {
//            return CommonResult.failed();
//        }
    }

    @ApiOperation("根据ID修改某个数据源")
    @PutMapping("/remoteDatasource/updateInfo")
    public CommonResult<String> update(@RequestBody SysRemoteDatabaseInfo sysRemoteDatabaseInfo0) {
        SysRemoteDatabaseInfo sysRemoteDatabaseInfo = remoteDatasourceService.getByID(sysRemoteDatabaseInfo0.getId());

        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        if(!cid.equals(sysRemoteDatabaseInfo.getCid())){
            return CommonResult.failed();
        }

        //判断是否仅修改了描述信息
        if (sysRemoteDatabaseInfo.getDbUrl().equals(sysRemoteDatabaseInfo0.getDbUrl())
                && sysRemoteDatabaseInfo.getDbUser().equals(sysRemoteDatabaseInfo0.getDbUser())
                && sysRemoteDatabaseInfo.getDbPassword().equals(sysRemoteDatabaseInfo0.getDbPassword())
                && sysRemoteDatabaseInfo.getTypeId() == sysRemoteDatabaseInfo0.getTypeId()) {
            sysRemoteDatabaseInfo.setDbDescription(sysRemoteDatabaseInfo0.getDbDescription());
            remoteDatasourceService.uptByID(sysRemoteDatabaseInfo);
            return CommonResult.success(null);
        }

        //body
        MultiValueMap<String, Object> paramMap = encap(sysRemoteDatabaseInfo.getId(), sysRemoteDatabaseInfo0.getTypeId());
        paramMap.add("db_url", sysRemoteDatabaseInfo0.getDbUrl());
        paramMap.add("db_user", sysRemoteDatabaseInfo0.getDbUser());
        paramMap.add("db_password", sysRemoteDatabaseInfo0.getDbPassword());
        paramMap.add("old_type", remoteDatasourceTypeService.getTypeByID(sysRemoteDatabaseInfo.getTypeId()));

        //PUT
        CommonResult commonResult = MyHttp.PUT(PrestoConfig.uptUrl(), paramMap);

        if (commonResult.getCode() == CodeConstant.SUCCESS.getCode()) {
            sysRemoteDatabaseInfo.setDbUrl(sysRemoteDatabaseInfo0.getDbUrl());
            sysRemoteDatabaseInfo.setDbUser(sysRemoteDatabaseInfo0.getDbUser());
            sysRemoteDatabaseInfo.setDbPassword(sysRemoteDatabaseInfo0.getDbPassword());
            sysRemoteDatabaseInfo.setDbDescription(sysRemoteDatabaseInfo0.getDbDescription());
            sysRemoteDatabaseInfo.setTypeId(sysRemoteDatabaseInfo0.getTypeId());
            remoteDatasourceService.uptByID(sysRemoteDatabaseInfo);
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID查看某数据源的所有数据库名")
    @GetMapping("/remoteDatasource/getAllSchemas")
    public CommonResult getAllSchemas(Long id) {
        String catlog = getNameByID(id);
        String sql = "show schemas from " + catlog;
        CommonResult commonResult = feignPrestoService.exeSQL(sql);
        return commonResult;
    }

    @ApiOperation("根据ID和数据库名查看某数据库的所有数据表名")
    @GetMapping("/remoteDatasource/getAllTables")
    public CommonResult getAllTables(Long id, String schemasName) {
        String catlog = getNameByID(id);
        String sql = "show tables from " + catlog + "." + schemasName;
        CommonResult commonResult = feignPrestoService.exeSQL(sql);
        return commonResult;
    }

    @ApiOperation("根据ID和数据库名查看某数据库的所有数据表名及属性名")
    @GetMapping("/remoteDatasource/getAllTablesInfo")
    public CommonResult getAllTablesInfo(Long id, String schemasName) {
        try{
            String root = getNameByID(id) + "." + schemasName;
            Map<String,Object> retRes = new HashMap<>();

            List<HashMap<String,Object>> results = new ArrayList<>();
            List<Map<String, Object>> tables = getResult("show tables from " + root);
            for(Map<String, Object> table:tables){
                String tableName = (String) table.get("Table");
                HashMap<String,Object> result = new HashMap<String,Object>();
                result.put("value",root + "." + tableName);
                result.put("label",tableName);
                List<Map<String, Object>> columns = getResult("show columns from " + root + "." + tableName);
                List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
                for(Map<String, Object> column:columns){
                    HashMap<String,Object> col = new HashMap<String,Object>();
                    col.put("value",root + "." + tableName + "." + column.get("Column"));
                    col.put("label",column.get("Column"));
                    map.add(col);
                }
                result.put("children",map);
                results.add(result);
            }
            retRes.put("rows",results);
            retRes.put("total",results.size());
            return CommonResult.success(retRes);
        }catch (Exception e){
            return CommonResult.failed(null);
        }

    }

    @ApiOperation("根据ID和数据库名和数据表名查看某数据表的所有属性名")
    @GetMapping("/remoteDatasource/getAllColumns")
    public CommonResult getAllColumns(Long id, String schemasName, String tableName) {
        String catlog = getNameByID(id);
        String sql = "show columns from " + catlog + "." + schemasName + "." + tableName;
        CommonResult commonResult = feignPrestoService.exeSQL(sql);
        return commonResult;
    }

    @ApiOperation("执行sql查询")
    @GetMapping("/remoteDatasource/exeSQL")
    public CommonResult exeSQL(String sql) {
        CommonResult commonResult = feignPrestoService.exeSQL(sql);
        return commonResult;
    }

    private MultiValueMap<String, Object> encap(Long db_id, Long type_id) {
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("id", db_id);
        paramMap.add("type", remoteDatasourceTypeService.getTypeByID(type_id));
        return paramMap;
    }

    private String getNameByID(Long id) {
        SysRemoteDatabaseInfo sysRemoteDatabaseInfo = remoteDatasourceService.getByID(id);
        String type = remoteDatasourceTypeService.getTypeByID(sysRemoteDatabaseInfo.getTypeId());
        return type + id;
    }

    private List<Map<String, Object>> getResult(String sql) {
        CommonResult commonResult = feignPrestoService.exeSQL(sql);
        return (List<Map<String, Object>>)((HashMap<String,Object>)commonResult.getData()).get("rows");
    }
}
