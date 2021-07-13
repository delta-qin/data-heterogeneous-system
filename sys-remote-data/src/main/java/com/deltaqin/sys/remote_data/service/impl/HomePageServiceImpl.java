package com.deltaqin.sys.remote_data.service.impl;

import com.deltaqin.sys.mapper.SysAdminMapper;
import com.deltaqin.sys.mapper.SysRemoteInfluxInfoMapper;
import com.deltaqin.sys.model.*;
import com.deltaqin.sys.remote_data.dao.SysAdminLoginLogMapperExtend;
import com.deltaqin.sys.remote_data.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private SysRemoteInfluxInfoMapper sysRemoteInfluxInfoMapper;

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private SysAdminLoginLogMapperExtend sysAdminLoginLogMapperExtend;

    @Override
    public int getInfluxSourceNum(Long cid) {
        SysRemoteInfluxInfoExample sysRemoteInfluxInfoExample = new SysRemoteInfluxInfoExample();
        sysRemoteInfluxInfoExample.createCriteria().andCidEqualTo(cid);

        List<SysRemoteInfluxInfo> sysRemoteInfluxInfos = sysRemoteInfluxInfoMapper.selectByExample(sysRemoteInfluxInfoExample);
        return sysRemoteInfluxInfos.size();
    }

    @Override
    public int getUserNum(Long cid) {
        SysAdminExample sysAdminExample = new SysAdminExample();
        sysAdminExample.createCriteria().andCidEqualTo(cid);

        List<SysAdmin> sysAdmins = sysAdminMapper.selectByExample(sysAdminExample);
        return sysAdmins.size();
    }

    @Override
    public int getTodayLoginNum(long cid) {
        return sysAdminLoginLogMapperExtend.getTodayLoginNum(cid);
    }

    @Override
    public void getSourceMap(Long cid) {
        SysRemoteInfluxInfoExample sysRemoteInfluxInfoExample = new SysRemoteInfluxInfoExample();
        sysRemoteInfluxInfoExample.createCriteria().andCidEqualTo(cid);

        List<SysRemoteInfluxInfo> sysRemoteInfluxInfos = sysRemoteInfluxInfoMapper.selectByExample(sysRemoteInfluxInfoExample);

    }
}
