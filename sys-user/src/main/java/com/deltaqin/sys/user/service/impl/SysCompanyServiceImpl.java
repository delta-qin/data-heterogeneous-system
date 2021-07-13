package com.deltaqin.sys.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.deltaqin.sys.common.exception.ValidateCodeException;
import com.deltaqin.sys.mapper.SysAdminMapper;
import com.deltaqin.sys.mapper.SysCompanyMapper;
import com.deltaqin.sys.model.SysAdmin;
import com.deltaqin.sys.model.SysCompany;
import com.deltaqin.sys.model.SysCompanyExample;
import com.deltaqin.sys.user.dao.SysCompanySearchDao;
import com.deltaqin.sys.user.dto.SysCompanyParam;
import com.deltaqin.sys.user.service.SysAdminService;
import com.deltaqin.sys.user.service.SysCompanyService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author deltaqin
 * @date 2020/9/29 3:23 下午
 */
@Service
public class SysCompanyServiceImpl implements SysCompanyService {

    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    @Autowired
    private SysCompanySearchDao sysCompanySearchDao;

    @Autowired
    private SysAdminMapper adminMapper;

    @Autowired
    private SysAdminService sysAdminService;

    @Override
    public int register(SysCompany sysCompany) throws ValidateCodeException {
        //查询是否有相同用户名的用户
        SysCompanyExample example = new SysCompanyExample();
        example.createCriteria().andNameEqualTo(sysCompany.getName());
        List<SysCompany> sysAdminList = sysCompanyMapper.selectByExample(example);
        if (sysAdminList.size() > 0) {
            throw new ValidateCodeException("公司已经注册");
        }

        //将密码进行加密操作
        String encodePassword = BCrypt.hashpw(sysCompany.getPassword());
        sysCompany.setPassword(encodePassword);
        int i = sysCompanyMapper.insertSelective(sysCompany);

        // 默认添加超级管理员
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setCid(sysCompany.getId());
        sysAdmin.setUsername(sysCompany.getName() + "root");
        sysAdmin.setNickName("root");
        sysAdmin.setCreateTime(new Date());
        sysAdmin.setStatus(1);

        //将密码进行加密操作
        encodePassword  = BCrypt.hashpw("root");
        sysAdmin.setPassword(encodePassword);
        Long insert = Long.valueOf(adminMapper.insertSelective(sysAdmin));
        List<Long> list = new ArrayList<>();
        list.add((long) 5);
        sysAdminService.updateRole(sysAdmin.getId(), list);

        return i;
    }

    @Override
    public SysCompany getByID(Long id) {
        SysCompany sysCompany = sysCompanyMapper.selectByPrimaryKey(id);
        return sysCompany;
    }

    @Override
    public List<SysCompany> getItem(String key, Integer pageNum, Integer pageSize) {
        if (StringUtils.isEmpty(key)){
            return null;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<SysCompany> sysCompanyByName = sysCompanySearchDao.getSysCompanyByName(key);
        return sysCompanyByName;
    }

    @Override
    public int delete(Long id) {
        int i = sysCompanyMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int update(SysCompanyParam sysCompanyParam) {
        SysCompany sysCompany = new SysCompany();
        sysCompany.setName(sysCompanyParam.getName());
        sysCompany.setId(sysCompanyParam.getId());
        SysCompany sysCompany1 = sysCompanyMapper.selectByPrimaryKey(sysCompanyParam.getId());
        if(BCrypt.checkpw(sysCompanyParam.getOldPassword(),sysCompany1.getPassword())){
            sysCompany.setPassword(BCrypt.hashpw(sysCompanyParam.getNewPassword()));
        } else {
            return 0;
        }

        int i = sysCompanyMapper.updateByPrimaryKeySelective(sysCompany);
        return i;
    }
}
