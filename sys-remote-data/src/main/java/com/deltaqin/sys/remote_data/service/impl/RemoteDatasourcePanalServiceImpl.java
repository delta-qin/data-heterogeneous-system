package com.deltaqin.sys.remote_data.service.impl;

import com.deltaqin.sys.mapper.SysRemoteDatabasePanalMapper;
import com.deltaqin.sys.model.SysRemoteDatabasePanal;
import com.deltaqin.sys.model.SysRemoteDatabasePanalExample;
import com.deltaqin.sys.remote_data.service.RemoteDatasourcePanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteDatasourcePanalServiceImpl implements RemoteDatasourcePanalService {
    @Autowired
    private SysRemoteDatabasePanalMapper sysRemoteDatabasePanalMapper;

//    @Autowired
//    @Qualifier("prestoTemplate")
//    JdbcTemplate jt ;

    @Override
    public void signInPanal(SysRemoteDatabasePanal sysRemoteDatabasePanal) {
        sysRemoteDatabasePanalMapper.insertSelective(sysRemoteDatabasePanal);
    }

    @Override
    public void deleteByID(Long ID,Long cid) {
        SysRemoteDatabasePanalExample sysRemoteDatabasePanalExample = new SysRemoteDatabasePanalExample();
        sysRemoteDatabasePanalExample.createCriteria().andIdEqualTo(ID).andCidEqualTo(cid);
        sysRemoteDatabasePanalMapper.deleteByExample(sysRemoteDatabasePanalExample);
    }

    @Override
    public void updataByID(SysRemoteDatabasePanal sysRemoteDatabasePanal) {
        sysRemoteDatabasePanalMapper.updateByPrimaryKeySelective(sysRemoteDatabasePanal);
    }

    @Override
    public List<SysRemoteDatabasePanal> getPanalByDbID(Long ID,Long cid) {
        SysRemoteDatabasePanalExample sysRemoteDatabasePanalExample = new SysRemoteDatabasePanalExample();
        sysRemoteDatabasePanalExample.createCriteria().andDbIdEqualTo(ID).andCidEqualTo(cid);
        List<SysRemoteDatabasePanal> panals = sysRemoteDatabasePanalMapper.selectByExample(sysRemoteDatabasePanalExample);
        return panals;
    }

    @Override
    public List<SysRemoteDatabasePanal> getAllPanalOnHomePage(Long cid) {
        SysRemoteDatabasePanalExample sysRemoteDatabasePanalExample = new SysRemoteDatabasePanalExample();
        sysRemoteDatabasePanalExample.createCriteria().andCidEqualTo(cid).andStateEqualTo(1);
        List<SysRemoteDatabasePanal> panals = sysRemoteDatabasePanalMapper.selectByExample(sysRemoteDatabasePanalExample);
        return panals;
    }

    @Override
    public List<SysRemoteDatabasePanal> getAllPanal(Long cid) {
        SysRemoteDatabasePanalExample sysRemoteDatabasePanalExample = new SysRemoteDatabasePanalExample();
        sysRemoteDatabasePanalExample.createCriteria().andCidEqualTo(cid);
        List<SysRemoteDatabasePanal> panals = sysRemoteDatabasePanalMapper.selectByExample(sysRemoteDatabasePanalExample);
        return panals;
    }

    @Override
    public boolean changeState(Long id, Long cid) {
        SysRemoteDatabasePanalExample sysRemoteDatabasePanalExample = new SysRemoteDatabasePanalExample();
        sysRemoteDatabasePanalExample.createCriteria().andCidEqualTo(cid).andIdEqualTo(id);
        List<SysRemoteDatabasePanal> panals = sysRemoteDatabasePanalMapper.selectByExample(sysRemoteDatabasePanalExample);
        if (panals.size() < 1) {
            return false;
        }
        SysRemoteDatabasePanal sysRemoteDatabasePanal = panals.get(0);
        if(sysRemoteDatabasePanal.getState() == 0){
            SysRemoteDatabasePanalExample sysRemoteDatabasePanalExample1 = new SysRemoteDatabasePanalExample();
            sysRemoteDatabasePanalExample1.createCriteria().andCidEqualTo(cid).andStateEqualTo(1);
            List<SysRemoteDatabasePanal> panals1 = sysRemoteDatabasePanalMapper.selectByExample(sysRemoteDatabasePanalExample1);
            if (panals1.size() >= 5) {
                return false;
            }
        }
        sysRemoteDatabasePanal.setState(1 - sysRemoteDatabasePanal.getState());
        sysRemoteDatabasePanalMapper.updateByPrimaryKeySelective(sysRemoteDatabasePanal);
        return true;
    }
}
