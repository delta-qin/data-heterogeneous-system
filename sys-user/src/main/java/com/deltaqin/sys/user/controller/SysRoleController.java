package com.deltaqin.sys.user.controller;

import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.model.SysMenu;
import com.deltaqin.sys.model.SysResource;
import com.deltaqin.sys.model.SysRole;
import com.deltaqin.sys.user.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "SysRoleController", value = "用户角色管理")
@RequestMapping("/role")
public class SysRoleController {
//    @Autowired
//    private SysRoleService roleService;

//    @ApiOperation("修改角色状态,1启用，0禁用")
//    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
//        SysRole sysRole = new SysRole();
//        sysRole.setStatus(status);
//        int count = roleService.update(id, sysRole);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }

//
//    @ApiOperation("添加角色")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult create(@RequestBody SysRole role) {
//        int count = roleService.create(role);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    @ApiOperation("修改角色")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@PathVariable Long id, @RequestBody SysRole role) {
//        int count = roleService.update(id, role);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    @ApiOperation("删除角色")
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
//        int count = roleService.delete(ids);
//        if (count > 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }
//
//    @ApiOperation("获取所有角色")
//    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<SysRole>> listAll() {
//        List<SysRole> roleList = roleService.list();
//        return CommonResult.success(roleList);
//    }


//
//    @ApiOperation("获取角色相关菜单")
//    @RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<SysMenu>> listMenu(@PathVariable Long roleId) {
//        List<SysMenu> roleList = roleService.listMenu(roleId);
//        return CommonResult.success(roleList);
//    }
//
//    @ApiOperation("获取角色相关资源")
//    @RequestMapping(value = "/listResource/{roleId}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<SysResource>> listResource(@PathVariable Long roleId) {
//        List<SysResource> roleList = roleService.listResource(roleId);
//        return CommonResult.success(roleList);
//    }
//
//    @ApiOperation("给角色分配菜单")
//    @RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
//        int count = roleService.allocMenu(roleId, menuIds);
//        return CommonResult.success(count);
//    }
//
//    @ApiOperation("给角色分配资源")
//    @RequestMapping(value = "/allocResource", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
//        int count = roleService.allocResource(roleId, resourceIds);
//        return CommonResult.success(count);
//    }

}
