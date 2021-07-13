package com.deltaqin.sys.user.controller;

import com.deltaqin.sys.common.entities.CommonPage;
import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.common.exception.ValidateCodeException;
import com.deltaqin.sys.model.SysCompany;
import com.deltaqin.sys.user.dto.SysCompanyParam;
import com.deltaqin.sys.user.service.SysCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author deltaqin
 * @date 2020/9/29 3:17 下午
 */
@RestController
@Api(tags = "SysCompanyController", value = "公司注册管理，")
@RequestMapping("/company")
@Slf4j
public class SysCompanyController {

    @Autowired
    private SysCompanyService sysCompanyService;

    @ApiOperation(value = "公司注册，默认添加超级管理员deltaqin deltaqin")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@Validated @RequestBody SysCompany sysCompany) throws ValidateCodeException {
        int register = sysCompanyService.register(sysCompany);
        if (register == 0) {
            return CommonResult.failed();
        }
        return CommonResult.success("注册成功");
    }

    @ApiOperation("获取所有公司列表")
    @RequestMapping(value = "/{key}",method = RequestMethod.GET)
    public CommonPage<SysCompany> getItem(@PathVariable String key, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<SysCompany> admin = sysCompanyService.getItem(key,  pageNum,  pageSize);
        return CommonPage.restPage(admin);
    }

    @ApiOperation("删除公司")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public CommonResult delete(@PathVariable Long id) {
        int admin = sysCompanyService.delete(id);
        return CommonResult.success("success");
    }

    @ApiOperation("修改公司信息")
    @RequestMapping(method = RequestMethod.POST)
    public CommonResult update(@RequestBody SysCompanyParam sysCompanyParam) {
        int admin = sysCompanyService.update(sysCompanyParam);
        if (admin == 0) return CommonResult.failed();
        return CommonResult.success("成功");
    }
}
