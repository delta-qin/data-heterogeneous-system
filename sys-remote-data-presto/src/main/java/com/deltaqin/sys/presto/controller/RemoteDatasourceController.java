package com.deltaqin.sys.presto.controller;

import com.deltaqin.sys.common.entities.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RemoteDatasourceController {
    @Autowired
    @Qualifier("prestoTemplate")
    JdbcTemplate jt;

    @ApiOperation("执行sql查询")
    @RequestMapping(value = "/remoteDatasource/exeSQL",method = RequestMethod.POST)
    public CommonResult exeSQL(@RequestParam("sql") String sql){
        try{
            List<Map<String, Object>> list = jt.queryForList(sql);
            HashMap<String, Object> results = new HashMap<>();
            results.put("total", list.size());
            results.put("rows", list);
            return CommonResult.success(results);
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.failed();
        }
    }
}
