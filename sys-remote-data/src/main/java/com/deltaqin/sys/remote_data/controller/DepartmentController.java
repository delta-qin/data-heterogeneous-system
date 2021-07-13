package com.deltaqin.sys.remote_data.controller;

import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.model.SysDepartmentInfo;
import com.deltaqin.sys.remote_data.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired(required = false)
    private HttpServletRequest httpServletRequest;

    @ApiOperation("增加部门")
    @PostMapping("/department/singin")
    public CommonResult addDepartment(@RequestParam String name) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        SysDepartmentInfo sysDepartmentInfo = new SysDepartmentInfo();
        sysDepartmentInfo.setName(name);
        sysDepartmentInfo.setCid(cid);
        if(departmentService.addDepartment(sysDepartmentInfo)){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/department/delete")
    public CommonResult deleteDepartment(@RequestParam Long id) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        if(departmentService.deleteaDepartment(id , cid)){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改部门")
    @PutMapping("/department/update")
    public CommonResult updateDepartment(@RequestBody SysDepartmentInfo sysDepartmentInfo) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        sysDepartmentInfo.setCid(cid);

        if(departmentService.updateDepartment(sysDepartmentInfo)){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询全部部门")
    @GetMapping("/department/getAll")
    public CommonResult getAllDepartment() {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        return CommonResult.success(departmentService.getDepartment(cid));
    }
}
