package com.deltaqin.sys.user.controller;

import com.deltaqin.sys.common.entities.CommonPage;
import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.model.SysResource;
import com.deltaqin.sys.user.service.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author deltaqin
 * @date 2020/9/9 10:55 下午
 */
@Controller
@Api(tags = "SysResourceController", value = "后台资源管理")
@RequestMapping("/resource")
public class SysResourceController {

    @Autowired
    private SysResourceService resourceService;

    @ApiOperation("初始化资源角色关联数据")
    @RequestMapping(value = "/initResourceRolesMap", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult initResourceRolesMap() {
        Map<String, List<String>> resourceRolesMap = resourceService.initResourceRolesMap();
        return CommonResult.success(resourceRolesMap);
    }


//    @ApiOperation("添加后台资源")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult create(@RequestBody SysResource sysResource) {
//        int count = resourceService.create(sysResource);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("修改后台资源")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@PathVariable Long id,
//                               @RequestBody SysResource sysResource) {
//        int count = resourceService.update(id, sysResource);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("根据ID获取资源详情")
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<SysResource> getItem(@PathVariable Long id) {
//        SysResource sysResource = resourceService.getItem(id);
//        return CommonResult.success(sysResource);
//    }
//
//    @ApiOperation("根据ID删除后台资源")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult delete(@PathVariable Long id) {
//        int count = resourceService.delete(id);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else {
//            return CommonResult.failed();
//        }
//    }
//
//    @ApiOperation("分页模糊查询后台资源")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<SysResource>> list(@RequestParam(required = false) Long categoryId,
//                                                      @RequestParam(required = false) String nameKeyword,
//                                                      @RequestParam(required = false) String urlKeyword,
//                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
//                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
//        List<SysResource> resourceList = resourceService.list(categoryId,nameKeyword, urlKeyword, pageSize, pageNum);
//        return CommonResult.success(CommonPage.restPage(resourceList));
//    }
//
//    @ApiOperation("查询所有后台资源")
//    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<SysResource>> listAll() {
//        List<SysResource> resourceList = resourceService.listAll();
//        return CommonResult.success(resourceList);
//    }

}
