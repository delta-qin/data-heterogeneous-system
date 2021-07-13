package com.deltaqin.sys.remote_data.controller;

import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.remote_data.dao.SysAdminLoginLogMapperExtend;
import com.deltaqin.sys.remote_data.service.HomePageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomePageController {
    @Autowired
    private SysAdminLoginLogMapperExtend sysAdminLoginLogMapperExtend;

    @Autowired
    private HomePageService homePageService;

    @Autowired(required = false)
    private HttpServletRequest httpServletRequest;

    @ApiOperation("获取本公司监控资源数目")
    @GetMapping("/getSourceNum")
    public CommonResult getSourceNum(){
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        Map<String, Object> result = new HashMap<>();
        result.put("total",homePageService.getInfluxSourceNum(cid));
        return CommonResult.success(result);
    }

    @ApiOperation("获取本公司用户数目")
    @GetMapping("/getUserNum")
    public CommonResult getUserNum(){
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        Map<String, Object> result = new HashMap<>();
        result.put("total",homePageService.getUserNum(cid));
        return CommonResult.success(result);
    }

    @ApiOperation("获取本公司今日登录人数")
    @GetMapping("/getLoginAtToday")
    public CommonResult getLoginAtToday(){
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        Map<String, Object> result = new HashMap<>();
        result.put("total",homePageService.getTodayLoginNum(cid));
        return CommonResult.success(result);
    }

    @ApiOperation("资源地图")
    @GetMapping("/getSourceMap")
    public CommonResult getSourceMap(){
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        List<Map<String, String>> sourceMap = sysAdminLoginLogMapperExtend.getSourceMap(cid);
        return CommonResult.success(sourceMap);
    }
}
