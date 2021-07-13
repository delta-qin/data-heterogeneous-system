package com.deltaqin.sys.user.service;

import com.deltaqin.sys.model.SysAdmin;

/**
 * @author deltaqin
 * @date 2020/9/8 7:05 下午
 */
public interface SysAdminCacheService {
    /**
     * 删除后台用户缓存
     */
    void delAdmin(Long adminId);

    /**
     * 获取缓存后台用户信息
     */
    SysAdmin getAdmin(Long adminId);

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(SysAdmin admin);
}
