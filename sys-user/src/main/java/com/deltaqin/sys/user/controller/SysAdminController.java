package com.deltaqin.sys.user.controller;

import cn.hutool.core.collection.CollUtil;
import com.deltaqin.sys.common.entities.CommonPage;
import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.common.entities.User;
import com.deltaqin.sys.common.exception.ValidateCodeException;
import com.deltaqin.sys.model.SysAdmin;
import com.deltaqin.sys.model.SysAdminLoginLog;
import com.deltaqin.sys.model.SysCompany;
import com.deltaqin.sys.model.SysRole;
import com.deltaqin.sys.user.dto.*;
import com.deltaqin.sys.user.service.SysAdminService;
import com.deltaqin.sys.user.service.SysCompanyService;
import com.deltaqin.sys.user.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author deltaqin
 * @date 2020/9/8 11:22 上午
 */
@RestController
@Api(tags = "SysAdminController", value = "管理员管理")
@RequestMapping("/admin")
@Slf4j
public class SysAdminController {
    @Autowired
    private SysAdminService adminService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysCompanyService sysCompanyService;

    @ApiOperation(value = "发送手机验证码")
    @PostMapping("code")
    public CommonResult<String> sendVerifyCode( String phone) {
        Boolean boo = this.adminService.sendVerifyCode(phone);
        if (boo == null || !boo) {
            return CommonResult.failed();
        }
        return CommonResult.success("发送成功");
    }

    @ApiOperation(value = "用户注册,ccode 为公司校验码，即公司注册密码")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<SysAdmin> register(@Validated @RequestBody SysAdminParam sysAdminParam ,
                                           @RequestParam String code,
                                           @RequestParam String ccode
    ) throws ValidateCodeException {
        SysAdmin sysAdmin = adminService.register(sysAdminParam, code,ccode);
        if (sysAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(sysAdmin);
    }

    @ApiOperation(value = "登录功能，返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@Validated @RequestBody SysAdminLoginParam sysAdminLoginParam) {
        CommonResult login = adminService.login(sysAdminLoginParam.getUsername(), sysAdminLoginParam.getPassword(), sysAdminLoginParam.getCid()  );
        System.out.println("111"+login.getCode());
        log.info("111",login.getCode());
        if (login.getCode()>=400){
            return CommonResult.failed(login.getMessage().replaceAll("org.springframework.security.authentication.InternalAuthenticationServiceException:",""));
        }
        return login;
    }

    @ApiOperation(value = "获取当前登录用户要显示的信息(用户名、角色、头像)")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult getAdminInfo() {
        SysAdmin sysAdmin = adminService.getCurrentAdmin();
        System.out.println("sysAdmin.getCid()"+sysAdmin.getCid());
        SysCompany byID = sysCompanyService.getByID(sysAdmin.getCid());
        Map<String, Object> data = new HashMap<>();
        data.put("username", sysAdmin.getUsername());
        data.put("nickName", sysAdmin.getNickName());
//        data.put("menus", roleService.getMenuList(sysAdmin.getId()));
        data.put("icon", sysAdmin.getIcon());
        data.put("phone", sysAdmin.getPhone());
        data.put("email", sysAdmin.getEmail());
        data.put("note", sysAdmin.getNote());
        data.put("id", sysAdmin.getId());
        data.put("company", byID.getName());
        List<SysRole> roleList = adminService.getRoleList(sysAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(SysRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登出功能，后台做不了啥，前台把token删了就可以了")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout() {
        return CommonResult.success(null);
    }


//    @ApiOperation("获取指定用户介绍信息")
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public CommonResult<SysAdmin> getItem(@PathVariable Long id) {
//        SysAdmin admin = adminService.getItem(id);
//        return CommonResult.success(admin);
//    }

    @ApiOperation("修改用户个人信息，前端这里不要写修改密码，另外写密码")
    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public CommonResult update(@RequestBody SysAdmin admin) {
        int count = adminService.update(admin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除指定用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改帐号状态，1可用，0不可用")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.GET)
    public CommonResult updateStatus(@PathVariable Long id) {
        int count = adminService.updateStatus(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("给用户分配角色,会删除原来的角色，系统目前设定一个用户只有一个角色（要么普通要么高级）")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

//    @ApiOperation("获取指定用户的角色")
//    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
//    public CommonResult<List<SysRole>> getRoleList(@PathVariable Long adminId) {
//        List<SysRole> roleList = adminService.getRoleList(adminId);
//        return CommonResult.success(roleList);
//    }


    @ApiOperation("分页获取普通管理员（id = 2）或者高级管理员（id = 5）列表")
    @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
    public CommonResult<CommonPage<AdminRole>> list(@RequestParam(value = "id", required = true) Long id,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<AdminRole> adminList = adminService.list(id, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("分页获取所有管理员列表")
    @RequestMapping(value = "/listAllByPage", method = RequestMethod.GET)
    public CommonResult<CommonPage<AdminRole>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<AdminRole> adminList = adminService.listAllByPage( pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }


    @ApiOperation("根据用户名获取通用用户信息（后端鉴权的时候feign需要使用，勿删）")
    @RequestMapping(value = "/loadByUsername", method = RequestMethod.GET)
    public User loadUserByUsername(@RequestParam String username) {
        User user = adminService.loadUserByUsername(username);
        return user;
    }

    @ApiOperation("根据用户名搜索用户。模糊查询")
    @RequestMapping(value = "/searchByUsername", method = RequestMethod.GET)
    public CommonResult<CommonPage<AdminRole>> searchByUsername(@RequestParam String username, @RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<AdminRole> user = adminService.searchByUsername(username, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(user));
    }

//    @ApiOperation("获取用户登录日志")
//    @RequestMapping(value = "/login/log", method = RequestMethod.GET)
//    public CommonResult<CommonPage<SysAdminLoginLog>> searchLoginLog(  ) {
//        List<SysAdminLoginLog> searchLoginLog = adminService.searchLoginLog();
//        return CommonResult.success(CommonPage.restPage(searchLoginLog));
//    }

    @ApiOperation("获取用户登录天数统计")
    @RequestMapping(value = "/login/log/day", method = RequestMethod.GET)
    public CommonResult<CommonPage<LoginLog>> searchLoginLogByDay(@RequestParam String username) {
        List<LoginLog> searchLoginLog = adminService.searchLoginLogByDay(username);
        return CommonResult.success(CommonPage.restPage(searchLoginLog));
    }

    @ApiOperation("获取用户登录地区统计")
    @RequestMapping(value = "/login/region", method = RequestMethod.GET)
    public CommonResult<CommonPage<SysLoginRegion>> searchLoginRegion(@RequestParam String username) {
        List<SysLoginRegion> searchSysLoginRegion = adminService.searchLoginRegion(username);
        return CommonResult.success(CommonPage.restPage(searchSysLoginRegion));
    }
}
