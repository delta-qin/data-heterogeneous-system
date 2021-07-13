package com.deltaqin.sys.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.common.utils.RedisService;
import com.deltaqin.sys.mapper.SysResourceMapper;
import com.deltaqin.sys.mapper.SysRoleMapper;
import com.deltaqin.sys.mapper.SysRoleResourceRelationMapper;
import com.deltaqin.sys.model.*;
import com.deltaqin.sys.user.service.SysResourceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author deltaqin
 * @date 2020/9/9 10:56 下午
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {
    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired(required = false)
    private RedisService redisService;
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public Map<String,List<String>> initResourceRolesMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
        List<SysResource> resourceList = resourceMapper.selectByExample(new SysResourceExample());
        List<SysRole> roleList = roleMapper.selectByExample(new SysRoleExample());
        List<SysRoleResourceRelation> relationList = roleResourceRelationMapper.selectByExample(new SysRoleResourceRelationExample());
        for (SysResource resource : resourceList) {
            Set<Long> roleIds = relationList.stream().filter(item -> item.getResourceId().equals(resource.getId())).map(SysRoleResourceRelation::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRoleMap.put("/"+applicationName+resource.getUrl(),roleNames);
//            resourceRoleMap.put("/"+"sys-influx"+resource.getUrl(),roleNames);
        }
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRoleMap);
        return resourceRoleMap;

    }

//
//    @Override
//    public int create(SysResource umsResource) {
//        umsResource.setCreateTime(new Date());
//        int count = resourceMapper.insert(umsResource);
//        initResourceRolesMap();
//        return count;
//    }
//
//    @Override
//    public int update(Long id, SysResource umsResource) {
//        umsResource.setId(id);
//        int count = resourceMapper.updateByPrimaryKeySelective(umsResource);
//        initResourceRolesMap();
//        return count;
//    }
//
//    @Override
//    public SysResource getItem(Long id) {
//        return resourceMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int delete(Long id) {
//        int count = resourceMapper.deleteByPrimaryKey(id);
//        initResourceRolesMap();
//        return count;
//    }
//
//    @Override
//    public List<SysResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
//        PageHelper.startPage(pageNum,pageSize);
//        SysResourceExample example = new SysResourceExample();
//        SysResourceExample.Criteria criteria = example.createCriteria();
//        if(categoryId!=null){
//            criteria.andCategoryIdEqualTo(categoryId);
//        }
//        if(StrUtil.isNotEmpty(nameKeyword)){
//            criteria.andNameLike('%'+nameKeyword+'%');
//        }
//        if(StrUtil.isNotEmpty(urlKeyword)){
//            criteria.andUrlLike('%'+urlKeyword+'%');
//        }
//        return resourceMapper.selectByExample(example);
//    }
//
//    @Override
//    public List<SysResource> listAll() {
//        return resourceMapper.selectByExample(new SysResourceExample());
//    }


}
