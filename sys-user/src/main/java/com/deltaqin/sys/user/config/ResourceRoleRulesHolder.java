package com.deltaqin.sys.user.config;

import com.deltaqin.sys.user.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 用于在项目初始化的时候在redis里面写入用户的权限
 * 方便之后在网关权限验证
 */
@Component
public class ResourceRoleRulesHolder {

    @Autowired
    private SysResourceService resourceService;


    // 注解的方法将会在依赖注入完成后被自动调用。
    @PostConstruct
    public void initResourceRolesMap(){
        resourceService.initResourceRolesMap();
    }
}
