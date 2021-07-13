package com.deltaqin.sys.user.dao;

import com.deltaqin.sys.model.SysAdminRoleRelation;
import com.deltaqin.sys.model.SysResource;
import com.deltaqin.sys.model.SysRole;
import com.deltaqin.sys.user.dto.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<SysAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<SysRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有可访问资源
     */
    List<SysResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);

    List<AdminRole> getAdminRole(@Param("id") Long id);

    List<AdminRole>  getAdminRoleByName(@Param("username") String username, Long cid);

    List<AdminRole>  listAllByPage();
}
