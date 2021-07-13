package com.deltaqin.sys.remote.service;

import com.deltaqin.sys.model.SysRemoteDatabasePredict;
import com.deltaqin.sys.model.SysRemoteInfluxPanal;

import java.util.List;
import java.util.Map;

public interface PredictService {
    boolean addPredict(SysRemoteDatabasePredict sysRemoteDatabasePredict);

    boolean uodatePredict(SysRemoteDatabasePredict sysRemoteDatabasePredict);

    boolean deleteByID(SysRemoteDatabasePredict sysRemoteDatabasePredict);

    List<Map<String,Object>> getAllPredict(Long cid);

    List<SysRemoteDatabasePredict> getAllPredictByPanalID(long cid,long panal_id);

    List<SysRemoteDatabasePredict> getAllPredict();

    SysRemoteInfluxPanal getPanalById(long id);

    boolean IsPredictExist(Long panalID);

    Boolean sendVerifyCode(String phone, String name, String first, String max, String maxValue);
}
