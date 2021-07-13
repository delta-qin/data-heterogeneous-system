package com.deltaqin.sys.remote_data.service.impl;

import com.deltaqin.sys.mapper.SysDepartmentInfoMapper;
import com.deltaqin.sys.model.SysDepartmentInfo;
import com.deltaqin.sys.model.SysDepartmentInfoExample;
import com.deltaqin.sys.remote_data.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private SysDepartmentInfoMapper sysDepartmentInfoMapper;

    @Override
    public Boolean addDepartment(SysDepartmentInfo sysDepartmentInfo) {
        int i = sysDepartmentInfoMapper.insertSelective(sysDepartmentInfo);
        return i==1;
    }

    @Override
    public Boolean deleteaDepartment(long id,long cid) {
        SysDepartmentInfoExample sysDepartmentInfoExample = new SysDepartmentInfoExample();
        sysDepartmentInfoExample.createCriteria().andIdEqualTo(id).andCidEqualTo(cid);

        int i = sysDepartmentInfoMapper.deleteByExample(sysDepartmentInfoExample);

        return i==1;
    }

    @Override
    public Boolean updateDepartment(SysDepartmentInfo sysDepartmentInfo) {
        SysDepartmentInfo departmentInfo = sysDepartmentInfoMapper.selectByPrimaryKey(sysDepartmentInfo.getId());
        if(departmentInfo.getCid()!=sysDepartmentInfo.getCid()){
            return false;
        }
        System.out.println(sysDepartmentInfo);
//        int i = sysDepartmentInfoMapper.updateByPrimaryKeySelective(sysDepartmentInfo);
        SysDepartmentInfoExample sysDepartmentInfoExample = new SysDepartmentInfoExample();
        sysDepartmentInfoExample.createCriteria().andIdEqualTo(sysDepartmentInfo.getId()).andCidEqualTo(sysDepartmentInfo.getCid());
        int i = sysDepartmentInfoMapper.updateByExampleSelective(sysDepartmentInfo, sysDepartmentInfoExample);
        return i!=0;
    }

    @Override
    public List<SysDepartmentInfo> getDepartment(long cid) {
        SysDepartmentInfoExample sysDepartmentInfoExample = new SysDepartmentInfoExample();
        sysDepartmentInfoExample.createCriteria().andCidEqualTo(cid);

        return sysDepartmentInfoMapper.selectByExample(sysDepartmentInfoExample);
    }
}
