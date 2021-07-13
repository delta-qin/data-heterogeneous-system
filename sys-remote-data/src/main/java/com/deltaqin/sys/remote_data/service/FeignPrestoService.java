package com.deltaqin.sys.remote_data.service;

import com.deltaqin.sys.common.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author deltaqin
 * @date 2020/9/29 8:20 下午
 */

@FeignClient("sys-remotedata-presto")
public interface FeignPrestoService {

    @RequestMapping(value = "/remoteDatasource/exeSQL",method = RequestMethod.POST)
    public CommonResult exeSQL(@RequestParam("sql") String sql);
}
