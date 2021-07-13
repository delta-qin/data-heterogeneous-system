package com.deltaqin.sys.remote_data.service;

import com.deltaqin.sys.model.SysRemoteDatabasePanal;

import java.util.List;

public interface RemoteDatasourcePanalService {
    void signInPanal(SysRemoteDatabasePanal sysRemoteDatabasePanal);

    void deleteByID(Long ID,Long cid);

    void updataByID(SysRemoteDatabasePanal sysRemoteDatabasePanal);

    List<SysRemoteDatabasePanal> getPanalByDbID(Long ID,Long cid);

    List<SysRemoteDatabasePanal> getAllPanalOnHomePage(Long cid);

    List<SysRemoteDatabasePanal> getAllPanal(Long cid);

    boolean changeState(Long id, Long cid);
}
