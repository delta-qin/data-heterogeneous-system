package com.deltaqin.sys.mapper;

import com.deltaqin.sys.model.SysRemoteInfluxInfo;
import com.deltaqin.sys.model.SysRemoteInfluxInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRemoteInfluxInfoMapper {
    long countByExample(SysRemoteInfluxInfoExample example);

    int deleteByExample(SysRemoteInfluxInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRemoteInfluxInfo record);

    int insertSelective(SysRemoteInfluxInfo record);

    List<SysRemoteInfluxInfo> selectByExample(SysRemoteInfluxInfoExample example);

    SysRemoteInfluxInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRemoteInfluxInfo record, @Param("example") SysRemoteInfluxInfoExample example);

    int updateByExample(@Param("record") SysRemoteInfluxInfo record, @Param("example") SysRemoteInfluxInfoExample example);

    int updateByPrimaryKeySelective(SysRemoteInfluxInfo record);

    int updateByPrimaryKey(SysRemoteInfluxInfo record);
}