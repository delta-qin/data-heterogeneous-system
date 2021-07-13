package com.deltaqin.sys.user.dao;

import com.deltaqin.sys.user.dto.LoginLog;
import com.deltaqin.sys.user.dto.SysLoginRegion;

import java.util.List;

/**
 * @author deltaqin
 * @date 2020/9/24 8:10 下午
 */
public interface SysLoginLogDao {
    List<LoginLog> getLoginLog(Long id, Long cid);

    List<SysLoginRegion> getRegion(Long id);
}
