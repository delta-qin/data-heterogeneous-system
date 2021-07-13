package com.deltaqin.sys.user.dao;

import com.deltaqin.sys.model.SysCompany;
import com.deltaqin.sys.user.dto.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author deltaqin
 * @date 2020/9/29 4:05 下午
 */
public interface SysCompanySearchDao {

    List<SysCompany> getSysCompanyByName(@Param("name") String name);

}
