package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysRemoteDatabasePanal;
import com.deltaqin.sys.model.SysRemoteDatabasePanalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRemoteDatabasePanalMapper {
    long countByExample(SysRemoteDatabasePanalExample example);

    int deleteByExample(SysRemoteDatabasePanalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRemoteDatabasePanal record);

    int insertSelective(SysRemoteDatabasePanal record);

    List<SysRemoteDatabasePanal> selectByExample(SysRemoteDatabasePanalExample example);

    SysRemoteDatabasePanal selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRemoteDatabasePanal record, @Param("example") SysRemoteDatabasePanalExample example);

    int updateByExample(@Param("record") SysRemoteDatabasePanal record, @Param("example") SysRemoteDatabasePanalExample example);

    int updateByPrimaryKeySelective(SysRemoteDatabasePanal record);

    int updateByPrimaryKey(SysRemoteDatabasePanal record);
}