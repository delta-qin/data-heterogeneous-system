package com.deltaqin.sys.user.service;

import com.deltaqin.sys.model.SysMenu;
import com.deltaqin.sys.model.SysResource;
import com.deltaqin.sys.model.SysRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author deltaqin
 * @date 2020/9/8 11:24 上午
 */
public interface SysRoleService {
    /**
     * 修改角色信息
     */
    int update(Long id, SysRole role);

//
//    /**
//     * 添加角色
//     */
//    int create(SysRole role);
//
//
//    /**
//     * 批量删除角色
//     */
//    int delete(List<Long> ids);
//
//
//    /**
//     * 获取所有角色列表
//     */
//    List<SysRole> list();
//
//    /**
//     * 根据管理员ID获取对应菜单
//     */
////    List<SysMenu> getMenuList(Long adminId);
//
//    /**
//     * 获取角色相关菜单
//     */
//    List<SysMenu> listMenu(Long roleId);
//
//    /**
//     * 获取角色相关资源
//     */
//    List<SysResource> listResource(Long roleId);
//
//    /**
//     * 给角色分配菜单
//     */
//    @Transactional
//    int allocMenu(Long roleId, List<Long> menuIds);
//
//    /**
//     * 给角色分配资源
//     */
//    @Transactional
//    int allocResource(Long roleId, List<Long> resourceIds);
}
