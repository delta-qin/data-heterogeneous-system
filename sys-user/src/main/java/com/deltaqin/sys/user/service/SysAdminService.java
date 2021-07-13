
package com.deltaqin.sys.user.service;

import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.common.entities.User;
import com.deltaqin.sys.common.exception.ValidateCodeException;
import com.deltaqin.sys.model.SysAdmin;
import com.deltaqin.sys.model.SysAdminLoginLog;
import com.deltaqin.sys.model.SysRole;
import com.deltaqin.sys.user.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author deltaqin
 * @date 2020/9/8 11:24 上午
 */
public interface SysAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    SysAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    SysAdmin register(SysAdminParam sysAdminParam, String code, String ccode) throws ValidateCodeException;

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @param cid
     * @return 调用认证中心返回结果
     */
    CommonResult login(String username, String password, Long cid);

    /**
     * 根据用户id获取用户
     */
//    SysAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户普通或者高级
     * @return
     */
    List<AdminRole> list(Long id, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(SysAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户角色
     */
    List<SysRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
//    List<SysResource> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    User loadUserByUsername(String username);

    /**
     * 获取当前登录后台用户
     */
    SysAdmin getCurrentAdmin();

    /**
     * 发送验证码到消息队列
     */
    Boolean sendVerifyCode(String phone);

    List<AdminRole> searchByUsername(String username, Integer pageSize, Integer pageNum);

//    List<SysAdminLoginLog> searchLoginLog();

    List<LoginLog> searchLoginLogByDay(String username);

    List<SysLoginRegion> searchLoginRegion(String username);

    List<AdminRole> listAllByPage(Integer pageSize, Integer pageNum);

    int updateStatus(Long id);
}
