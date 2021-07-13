package com.deltaqin.sys.user.service;

import com.deltaqin.sys.common.exception.ValidateCodeException;
import com.deltaqin.sys.model.SysCompany;
import com.deltaqin.sys.user.dto.SysCompanyParam;

import java.util.List;

/**
 * @author deltaqin
 * @date 2020/9/29 3:23 下午
 */
public interface SysCompanyService {


    int register(SysCompany sysCompany) throws ValidateCodeException;

    SysCompany getByID(Long id);

    List<SysCompany> getItem(String key, Integer pageNum, Integer pageSize);

    int delete(Long id);

    int update(SysCompanyParam sysCompanyParam);
}
