package com.deltaqin.sys.remote.service.impl;

import com.deltaqin.sys.common.utils.NumberUtils;
import com.deltaqin.sys.mapper.SysRemoteDatabasePredictMapper;
import com.deltaqin.sys.mapper.SysRemoteInfluxPanalMapper;
import com.deltaqin.sys.model.SysRemoteDatabasePredict;
import com.deltaqin.sys.model.SysRemoteDatabasePredictExample;
import com.deltaqin.sys.model.SysRemoteInfluxPanal;
import com.deltaqin.sys.remote.service.PredictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PredictServiceImpl implements PredictService {
    @Autowired
    private SysRemoteDatabasePredictMapper sysRemoteDatabasePredictMapper;

    @Autowired
    private SysRemoteInfluxPanalMapper sysRemoteInfluxPanalMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public boolean addPredict(SysRemoteDatabasePredict sysRemoteDatabasePredict) {
        int i = sysRemoteDatabasePredictMapper.insertSelective(sysRemoteDatabasePredict);
        return i==1;
    }

    @Override
    public boolean uodatePredict(SysRemoteDatabasePredict sysRemoteDatabasePredict) {
        int i = sysRemoteDatabasePredictMapper.updateByPrimaryKeySelective(sysRemoteDatabasePredict);
        return i==1;
    }

    @Override
    public boolean deleteByID(SysRemoteDatabasePredict sysRemoteDatabasePredict) {
        SysRemoteDatabasePredictExample sysRemoteDatabasePredictExample = new SysRemoteDatabasePredictExample();
        sysRemoteDatabasePredictExample
                .createCriteria()
                .andIdEqualTo(sysRemoteDatabasePredict.getId())
                .andCidEqualTo(sysRemoteDatabasePredict.getCid());
        int i = sysRemoteDatabasePredictMapper.deleteByExample(sysRemoteDatabasePredictExample);
        return i==1;
    }

    @Override
    public List<Map<String,Object>> getAllPredict(Long cid) {
        SysRemoteDatabasePredictExample sysRemoteDatabasePredictExample = new SysRemoteDatabasePredictExample();
        sysRemoteDatabasePredictExample
                .createCriteria()
                .andCidEqualTo(cid);
        List<SysRemoteDatabasePredict> sysRemoteDatabasePredicts = sysRemoteDatabasePredictMapper.selectByExample(sysRemoteDatabasePredictExample);
        List<Map<String,Object>> results = new ArrayList<>();
        for(SysRemoteDatabasePredict sysRemoteDatabasePredict:sysRemoteDatabasePredicts){
            SysRemoteInfluxPanal sysRemoteInfluxPanal = sysRemoteInfluxPanalMapper.selectByPrimaryKey(sysRemoteDatabasePredict.getPanalId());
            Map<String,Object> result = new HashMap<>();
            result.put("panal",sysRemoteInfluxPanal);
            result.put("predict",sysRemoteDatabasePredict);
            results.add(result);
        }
        return results;
    }

    @Override
    public List<SysRemoteDatabasePredict> getAllPredictByPanalID(long cid, long panal_id) {
        SysRemoteDatabasePredictExample sysRemoteDatabasePredictExample = new SysRemoteDatabasePredictExample();
        sysRemoteDatabasePredictExample
                .createCriteria()
                .andCidEqualTo(cid)
                .andPanalIdEqualTo(panal_id);
        List<SysRemoteDatabasePredict> sysRemoteDatabasePredicts = sysRemoteDatabasePredictMapper.selectByExample(sysRemoteDatabasePredictExample);
        return sysRemoteDatabasePredicts;
    }

    @Override
    public List<SysRemoteDatabasePredict> getAllPredict() {
        SysRemoteDatabasePredictExample sysRemoteDatabasePredictExample = new SysRemoteDatabasePredictExample();
        sysRemoteDatabasePredictExample.createCriteria();
        List<SysRemoteDatabasePredict> sysRemoteDatabasePredicts = sysRemoteDatabasePredictMapper.selectByExample(sysRemoteDatabasePredictExample);
        return sysRemoteDatabasePredicts;
    }



    @Override
    public SysRemoteInfluxPanal getPanalById(long id) {
        return sysRemoteInfluxPanalMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean IsPredictExist(Long panalID) {
        SysRemoteDatabasePredictExample sysRemoteDatabasePredictExample = new SysRemoteDatabasePredictExample();
        sysRemoteDatabasePredictExample.createCriteria().andPanalIdEqualTo(panalID);
        List<SysRemoteDatabasePredict> sysRemoteDatabasePredicts = sysRemoteDatabasePredictMapper.selectByExample(sysRemoteDatabasePredictExample);
        return sysRemoteDatabasePredicts.size()==0?false:true;
    }

    @Override
    public Boolean sendVerifyCode(String phone,String name,String first,String max, String maxValue) {
        System.out.println("service短信");
        // 生成验证码
        String code = NumberUtils.generateCode(6);
        try {
            // 发送短信
            Map<String, String> msg = new HashMap<>();
            msg.put("phone", phone);
            msg.put("name", name);
            msg.put("first",first);
            msg.put("max",max);
            msg.put("maxValue",maxValue);

            // 坑爹啊，这里写的时候routing key和消费者那里的不一样，我说为什么一直不消费
            this.amqpTemplate.convertAndSend("predict.sms.exchange", "predict.verify.code", msg);
            // 将code存入redis

            return true;
        } catch (Exception e) {
            log.error("发送短信失败。phone：{}， code：{}", phone, code);
            return false;
        }
    }
}
