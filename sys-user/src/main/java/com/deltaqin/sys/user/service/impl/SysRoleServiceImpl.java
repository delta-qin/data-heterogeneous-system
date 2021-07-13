package com.deltaqin.sys.user.service.impl;

import com.deltaqin.sys.mapper.SysRoleMapper;
import com.deltaqin.sys.model.*;
import com.deltaqin.sys.user.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author deltaqin
 * @date 2020/9/8 2:59 下午
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired(required = false)
    private SysRoleMapper roleMapper;
//    @Autowired
//    private SysRoleMenuRelationMapper roleMenuRelationMapper;
//    @Autowired
//    private SysRoleResourceRelationMapper roleResourceRelationMapper;
//    @Autowired
//    private SysRoleDao roleDao;
//    @Autowired
//    private SysResourceService resourceService;

    @Override
    public int update(Long id, SysRole role) {
        role.setId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }
//
//    @Override
//    public int create(SysRole role) {
//        role.setCreateTime(new Date());
//        role.setAdminCount(0);
//        role.setSort(0);
//        return roleMapper.insert(role);
//    }
//
//
//
//    @Override
//    public int delete(List<Long> ids) {
//        SysRoleExample example = new SysRoleExample();
//        example.createCriteria().andIdIn(ids);
//        int count = roleMapper.deleteByExample(example);
//        resourceService.initResourceRolesMap();
//        return count;
//    }
//
//    @Override
//    public List<SysRole> list() {
//        return roleMapper.selectByExample(new SysRoleExample());
//    }
//
////    @Override
////    public List<SysRole> list(String keyword, Integer pageSize, Integer pageNum) {
////        PageHelper.startPage(pageNum, pageSize);
////        SysRoleExample example = new SysRoleExample();
////        if (!StringUtils.isEmpty(keyword)) {
////            example.createCriteria().andNameLike("%" + keyword + "%");
////        }
////        return roleMapper.selectByExample(example);
////    }
//
////    @Override
////    public List<SysMenu> getMenuList(Long adminId) {
////        return roleDao.getMenuList(adminId);
////    }
//
//    @Override
//    public List<SysMenu> listMenu(Long roleId) {
//        return roleDao.getMenuListByRoleId(roleId);
//    }
//
//    @Override
//    public List<SysResource> listResource(Long roleId) {
//        return roleDao.getResourceListByRoleId(roleId);
//    }
//
//    @Override
//    public int allocMenu(Long roleId, List<Long> menuIds) {
//        //先删除原有关系
//        SysRoleMenuRelationExample example=new SysRoleMenuRelationExample();
//        example.createCriteria().andRoleIdEqualTo(roleId);
//        roleMenuRelationMapper.deleteByExample(example);
//        //批量插入新关系
//        for (Long menuId : menuIds) {
//            SysRoleMenuRelation relation = new SysRoleMenuRelation();
//            relation.setRoleId(roleId);
//            relation.setMenuId(menuId);
//            roleMenuRelationMapper.insert(relation);
//        }
//        return menuIds.size();
//    }
//
//    @Override
//    public int allocResource(Long roleId, List<Long> resourceIds) {
//        //先删除原有关系
//        SysRoleResourceRelationExample example=new SysRoleResourceRelationExample();
//        example.createCriteria().andRoleIdEqualTo(roleId);
//        roleResourceRelationMapper.deleteByExample(example);
//        //批量插入新关系
//        for (Long resourceId : resourceIds) {
//            SysRoleResourceRelation relation = new SysRoleResourceRelation();
//            relation.setRoleId(roleId);
//            relation.setResourceId(resourceId);
//            roleResourceRelationMapper.insert(relation);
//        }
//        resourceService.initResourceRolesMap();
//        return resourceIds.size();
//    }
}
