package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysRemoteInfluxPanal;
import com.deltaqin.sys.model.SysRemoteInfluxPanalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRemoteInfluxPanalMapper {
    long countByExample(SysRemoteInfluxPanalExample example);

    int deleteByExample(SysRemoteInfluxPanalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRemoteInfluxPanal record);

    int insertSelective(SysRemoteInfluxPanal record);

    List<SysRemoteInfluxPanal> selectByExample(SysRemoteInfluxPanalExample example);

    SysRemoteInfluxPanal selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRemoteInfluxPanal record, @Param("example") SysRemoteInfluxPanalExample example);

    int updateByExample(@Param("record") SysRemoteInfluxPanal record, @Param("example") SysRemoteInfluxPanalExample example);

    int updateByPrimaryKeySelective(SysRemoteInfluxPanal record);

    int updateByPrimaryKey(SysRemoteInfluxPanal record);
}